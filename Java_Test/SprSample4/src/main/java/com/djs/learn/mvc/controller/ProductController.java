
package com.djs.learn.mvc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.djs.learn.mvc.domain.Product;
import com.djs.learn.mvc.exception.NoProductsFoundUnderCategoryException;
import com.djs.learn.mvc.exception.ProductNotFoundException;
import com.djs.learn.mvc.service.ProductService;
import com.djs.learn.mvc.validator.ProductValidatorWrapper;

// For class, "market" = "/market".
// The full path is "/market".

// For method, "products" = "/products".
// The full path is "/market/products".

@Controller
@RequestMapping("market")
public class ProductController
{
	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductValidatorWrapper productValidatorWrapper;

	@RequestMapping("products")
	public String list(Model model){
		logger.info("[list]");

		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("update/stock")
	public String updateStock(Model model){
		logger.info("[updateStock]");

		productService.updateAllStock();
		return "redirect:/market/products";
	}

	// Test: @PathVariable
	// Test: http://localhost:8080/SprSample4/market/products/Tablet
	// Test: http://localhost:8080/SprSample4/market/products/Laptop

	// Test exception not captured by handler.
	// Test: http://localhost:8080/SprSample4/market/products/Unknown

	@RequestMapping("products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
		logger.info("[getProductsByCategory]");

		List<Product> products = productService.getProductsByCategory(productCategory);

		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}

		model.addAttribute("products", products);
		return "products";
	}

	// Test: @MatrixVariable
	// Test: http://localhost:8080/SprSample4/market/products/filter/price;low=500;high=1000

	@RequestMapping("products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model){
		logger.info("[getProductsByFilter]");

		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	// Test: @RequestParam
	// Test: http://localhost:8080/SprSample4/market/product?id=P1234

	@RequestMapping("product")
	public String getProductById(@RequestParam("id") String productId, Model model){
		logger.info("[getProductById]");

		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	// Test: http://localhost:8080/SprSample4/market/products/add

	@RequestMapping(value = "products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		logger.info("[getAddNewProductForm<GET>]");

		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	// Test: Invoked from "addProduct.jsp" by POST submit.
	// javax.validation.Valid will invoke  javax.validation.constraints defined in Product class.

	@RequestMapping(value = "products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request){
		logger.info("[getAddNewProductForm<POST>]");

		// Validated errors by @Valid will also add in BindingResult.
		if (result.hasErrors()) {
			return "addProduct";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		// The input parameter "HttpServletRequest request" is for this section.
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
		logger.info("[initialiseBinder]");

		// If use Spring Validator, instead of Spring Validator wrapper,
		// The JSR-303/Bean Validation annotations will be ignored by compiler!
		// binder.setValidator(productValidator);

		// It should use Spring Validator wrapper, to wrap both Spring Validator and JSR-303/Bean Validation.
		binder.setValidator(productValidatorWrapper);

		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "condition", "productImage",
		                        "language");
	}

	// Test: @ExceptionHandler
	// Test: http://localhost:8080/SprSample4/market/product?id=P1000

	// One ExceptionHandler for one certain exception.
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception){
		logger.info("[handleError]");

		ModelAndView view = new ModelAndView();

		view.addObject("invalidProductId", exception.getProductId());
		view.addObject("exception", exception);
		view.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		// JSP file name.
		view.setViewName("productNotFound");

		return view;
	}

	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode(){
		logger.info("[invalidPromoCode]");

		return "invalidPromoCode";
	}
}
