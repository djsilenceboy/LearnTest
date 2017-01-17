
package com.djs.learn.mvc.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.djs.learn.mvc.domain.ProductRepository;
import com.djs.learn.mvc.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController
{
	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model){
		logger.info(this.getClass().getName() + ":list");
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model){
		logger.info(this.getClass().getName() + ":updateStock");
		productService.updateAllStock();
		return "redirect:/products";
	}

	// Test: http://<IP>:8080/SprSample4/market/products/Tablet
	// Test: http://<IP>:8080/SprSample4/market/products/Laptop

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
		logger.info(this.getClass().getName() + ":getProductsByCategory");
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	// Test: http://<IP>:8080/SprSample4/market/products/filter/price;low=500;high=1000

	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model){
		logger.info(this.getClass().getName() + ":getProductsByFilter");
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model){
		logger.info(this.getClass().getName() + ":getProductById");
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
}
