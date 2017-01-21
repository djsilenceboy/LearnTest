
package com.djs.learn.mvc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.djs.learn.mvc.domain.Product;
import com.djs.learn.mvc.domain.ProductRepository;
import com.djs.learn.mvc.service.ProductService;

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
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@RequestMapping("products")
	public String list(Model model){
		logger.info(this.getClass().getName() + ":list");

		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("update/stock")
	public String updateStock(Model model){
		logger.info(this.getClass().getName() + ":updateStock");

		productService.updateAllStock();
		return "redirect:/market/products";
	}

	// Test: @PathVariable
	// Test: http://localhost:8080/SprSample4/market/products/Tablet
	// Test: http://localhost:8080/SprSample4/market/products/Laptop

	@RequestMapping("products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
		logger.info(this.getClass().getName() + ":getProductsByCategory");

		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	// Test: @MatrixVariable
	// Test: http://localhost:8080/SprSample4/market/products/filter/price;low=500;high=1000

	@RequestMapping("products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model){
		logger.info(this.getClass().getName() + ":getProductsByFilter");

		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	// Test: @RequestParam
	// Test: http://localhost:8080/SprSample4/market/product?id=P1234

	@RequestMapping("product")
	public String getProductById(@RequestParam("id") String productId, Model model){
		logger.info(this.getClass().getName() + ":getProductById");

		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	// Test: http://localhost:8080/SprSample4/market/products/add

	@RequestMapping(value = "products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		logger.info(this.getClass().getName() + ":getAddNewProductForm.GET");

		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	// Test: Invoked from "addProduct.jsp" by POST submit.

	@RequestMapping(value = "products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request){
		logger.info(this.getClass().getName() + ":getAddNewProductForm.POST");

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
		logger.info(this.getClass().getName() + ":initialiseBinder");

		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "condition", "productImage");
	}
}
