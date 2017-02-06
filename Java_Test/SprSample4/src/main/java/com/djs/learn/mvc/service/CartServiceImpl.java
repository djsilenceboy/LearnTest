
package com.djs.learn.mvc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djs.learn.mvc.domain.Cart;
import com.djs.learn.mvc.domain.CartRepository;
import com.djs.learn.mvc.dto.CartDto;
import com.djs.learn.mvc.exception.InvalidCartException;

@Service
public class CartServiceImpl implements CartService
{
	private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

	@Autowired
	private CartRepository cartRepository;

	public void create(CartDto cartDto){
		logger.info("[create]");

		cartRepository.create(cartDto);
	}

	@Override
	public Cart read(String id){
		logger.info("[read]");

		return cartRepository.read(id);
	}

	@Override
	public void update(String id, CartDto cartDto){
		logger.info("[update]");

		cartRepository.update(id, cartDto);
	}

	@Override
	public void delete(String id){
		logger.info("[delete]");

		cartRepository.delete(id);
	}

	@Override
	public void addItem(String cartId, String productId){
		logger.info("[addItem]");

		cartRepository.addItem(cartId, productId);
	}

	@Override
	public void removeItem(String cartId, String productId){
		logger.info("[removeItem]");

		cartRepository.removeItem(cartId, productId);
	}

	@Override
	public Cart validate(String cartId){
		logger.info("[validate]");

		Cart cart = cartRepository.read(cartId);
		if (cart == null || cart.getCartItems().size() == 0) {
			throw new InvalidCartException(cartId);
		}

		return cart;
	}

	@Override
	public void clearCart(String cartId){
		logger.info("[clearCart]");

		cartRepository.clearCart(cartId);
	}
}
