
package com.djs.learn.mvc.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShippingDetail implements Serializable
{
	private static final long serialVersionUID = 6350930334140807514L;

	private Long id;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;

	public Long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public ShippingDetail(){
		this.shippingAddress = new Address();
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Date getShippingDate(){
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate){
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress(){
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress){
		this.shippingAddress = shippingAddress;
	}

	@Override
	public int hashCode(){
		return ((id == null) ? 0 : id.hashCode()) * ((name == null) ? 0 : name.hashCode());
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof ShippingDetail) {
			ShippingDetail shippingDetail = (ShippingDetail)obj;
			if ((id != null) && (shippingDetail.id != null) && id.equals(shippingDetail.id) && (name != null) && (shippingDetail.name != null)
			        && name.equals(shippingDetail.name))
			    return true;
		}

		return false;
	}
}
