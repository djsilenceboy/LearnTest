
package com.djs.learn.mvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable
{
	private static final long serialVersionUID = -4546941350577482213L;

	private String id;
	private Product product;
	private int quantity;
	private BigDecimal totalPrice;

	public CartItem(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public Product getProduct(){
		return product;
	}

	public void setProduct(Product product){
		this.product = product;
		this.updateTotalPrice();
	}

	public int getQuantity(){
		return quantity;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice(){
		this.updateTotalPrice();
		return totalPrice;
	}

	public void updateTotalPrice(){
		totalPrice = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
	}

	@Override
	public int hashCode(){
		return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof CartItem) {
			CartItem cartItem = (CartItem)obj;
			if ((id != null) && (cartItem.id != null) && id.equals(cartItem.id)) return true;
		}

		return false;
	}
}
