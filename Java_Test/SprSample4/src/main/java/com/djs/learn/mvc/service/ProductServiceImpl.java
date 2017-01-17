
package com.djs.learn.mvc.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djs.learn.mvc.domain.Product;
import com.djs.learn.mvc.domain.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock(){
		logger.info(this.getClass().getName() + ":updateAllStock");
		List<Product> allProducts = productRepository.getAllProducts();

		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
		}
	}

	@Override
	public List<Product> getAllProducts(){
		return productRepository.getAllProducts();
	}

	public List<Product> getProductsByCategory(String category){
		return productRepository.getProductsByCategory(category);
	}

	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams){
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID){
		return productRepository.getProductById(productID);
	}
}
