
package com.djs.learn.mvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class Cart implements Serializable
{
	private static final long serialVersionUID = 6554623865768217431L;

	private String id;
	private List<CartItem> cartItems;
	private BigDecimal grandTotal;

	public Cart(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public BigDecimal getGrandTotal(){
		updateGrandTotal();
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal){
		this.grandTotal = grandTotal;
	}

	public List<CartItem> getCartItems(){
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems){
		this.cartItems = cartItems;
	}

	public CartItem getItemByProductId(String productId){
		return cartItems.stream().filter(cartItem -> cartItem.getProduct().getProductId().equals(productId)).findAny().orElse(null);
	}

	public void updateGrandTotal(){
		Function<CartItem, BigDecimal> totalMapper = cartItem -> cartItem.getTotalPrice();

		BigDecimal grandTotal = cartItems.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

		this.setGrandTotal(grandTotal);
	}

	@Override
	public int hashCode(){
		return ((id == null) ? 0 : id.hashCode()) * ((grandTotal == null) ? 0 : grandTotal.hashCode());
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof Cart) {
			Cart cart = (Cart)obj;
			if ((id != null) && (cart.id != null) && id.equals(cart.id)) return true;
		}

		return false;
	}
}
