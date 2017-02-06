
package com.djs.learn.mvc.domain;

import java.io.Serializable;

public class Customer implements Serializable
{
	private static final long serialVersionUID = 2284040482222162898L;

	private Long customerId;
	private String name;
	private Address billingAddress;
	private String phoneNumber;

	public Customer(){
		super();
		this.billingAddress = new Address();
	}

	public Customer(Long customerId, String name){
		this();
		this.customerId = customerId;
		this.name = name;
	}

	public Long getCustomerId(){
		return customerId;
	}

	public void setCustomerId(long customerId){
		this.customerId = customerId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Address getBillingAddress(){
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress){
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public static long getSerialversionuid(){
		return serialVersionUID;
	}

	@Override
	public int hashCode(){
		return ((customerId == null) ? 0 : customerId.hashCode()) * ((name == null) ? 0 : name.hashCode());
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof Customer) {
			Customer customer = (Customer)obj;
			if ((customerId != null) && (customer.customerId != null) && customerId.equals(customer.customerId) && (name != null) && (customer.name != null)
			        && name.equals(customer.name))
			    return true;
		}

		return false;
	}
}
