
package com.djs.learn.mvc.service;

import java.util.List;
import java.util.Map;

import com.djs.learn.mvc.domain.Product;

public interface ProductService
{
	void updateAllStock();

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductById(String productID);

	void addProduct(Product product);
}
