
package com.djs.learn.mvc.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.djs.learn.mvc.service.ProductService;

public class CartItemMapper implements RowMapper<CartItem>
{
	private static final Logger logger = Logger.getLogger(CartItemMapper.class);

	private ProductService productService;

	public CartItemMapper(ProductService productService){
		this.productService = productService;
	}

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException{
		logger.info("[mapRow]");

		CartItem cartItem = new CartItem(rs.getString("ID"));

		cartItem.setProduct(productService.getProductById(rs.getString("PRODUCT_ID")));
		cartItem.setQuantity(rs.getInt("QUANTITY"));

		return cartItem;
	}
}
