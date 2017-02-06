
package com.djs.learn.mvc.domain;

import java.io.Serializable;

public class Address implements Serializable
{
	private static final long serialVersionUID = -530086768384258062L;

	private Long id;
	private String doorNo;
	private String streetName;
	private String areaName;
	private String state;
	private String country;
	private String zipCode;

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getDoorNo(){
		return doorNo;
	}

	public void setDoorNo(String doorNo){
		this.doorNo = doorNo;
	}

	public String getStreetName(){
		return streetName;
	}

	public void setStreetName(String streetName){
		this.streetName = streetName;
	}

	public String getAreaName(){
		return areaName;
	}

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getZipCode(){
		return zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	@Override
	public int hashCode(){
		return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof Address) {
			Address address = (Address)obj;
			if ((id != null) && (address.id != null) && id.equals(address.id)) return true;
		}

		return false;
	}
}
