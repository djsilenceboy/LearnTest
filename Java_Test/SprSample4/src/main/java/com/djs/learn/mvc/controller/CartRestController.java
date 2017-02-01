
package com.djs.learn.mvc.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.djs.learn.mvc.domain.Cart;
import com.djs.learn.mvc.dto.CartDto;
import com.djs.learn.mvc.service.CartService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController
{
	private static final Logger logger = Logger.getLogger(CartRestController.class);

	@Autowired
	private CartService cartService;

	// Test: http://localhost:8080/SprSample4/rest/cart

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody CartDto cartDto){
		logger.info("[create]");

		cartService.create(cartDto);
	}

	// Test: http://localhost:8080/SprSample4/rest/cart/Ca1234

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public Cart read(@PathVariable(value = "cartId") String cartId){
		logger.info("[read]");

		return cartService.read(cartId);
	}

	// Test: http://localhost:8080/SprSample4/rest/cart/Ca1234

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto){
		logger.info("[update]");

		cartDto.setId(cartId);
		cartService.update(cartId, cartDto);
	}

	// Test: http://localhost:8080/SprSample4/rest/cart/Ca1234

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "cartId") String cartId){
		logger.info("[delete]");

		cartService.delete(cartId);
	}

	// Test: http://localhost:8080/SprSample4/rest/cart/add/It1234

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void addItem(@PathVariable String productId, HttpSession session){
		logger.info("[addItem]");

		cartService.addItem(session.getId(), productId);
	}

	// Test: http://localhost:8080/SprSample4/rest/cart/remove/It1234

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void removeItem(@PathVariable String productId, HttpSession session){
		logger.info("[removeItem]");

		cartService.removeItem(session.getId(), productId);
	}
}
