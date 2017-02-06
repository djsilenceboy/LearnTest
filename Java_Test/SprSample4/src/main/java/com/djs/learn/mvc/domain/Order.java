
package com.djs.learn.mvc.domain;

import java.io.Serializable;

public class Order implements Serializable
{
	private static final long serialVersionUID = -3560539622417210365L;

	private Long orderId;
	private Cart cart;
	private Customer customer;
	private ShippingDetail shippingDetail;

	public Order(){
		this.customer = new Customer();
		this.shippingDetail = new ShippingDetail();
	}

	public Long getOrderId(){
		return orderId;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Cart getCart(){
		return cart;
	}

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public Customer getCustomer(){
		return customer;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public ShippingDetail getShippingDetail(){
		return shippingDetail;
	}

	public void setShippingDetail(ShippingDetail shippingDetail){
		this.shippingDetail = shippingDetail;
	}

	@Override
	public int hashCode(){
		return (orderId == null) ? 0 : orderId.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof Order) {
			Order order = (Order)obj;
			if ((orderId != null) && (order.orderId != null) && orderId.equals(order.orderId)) return true;
		}

		return false;
	}
}
