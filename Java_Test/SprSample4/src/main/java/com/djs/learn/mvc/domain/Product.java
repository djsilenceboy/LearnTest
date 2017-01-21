
package com.djs.learn.mvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

// "@XmlRootElement" is for MarshallingView.
@XmlRootElement
public class Product implements Serializable
{
	private static final long serialVersionUID = 3678107792576131001L;

	private String productId;
	private String name;
	private BigDecimal unitPrice;
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;
	// "@JsonIgnore" is for MappingJackson2JsonView to exclude the field from JSON.
	@JsonIgnore
	private MultipartFile productImage;

	public Product(){
		super();
	}

	public Product(String productId, String name, BigDecimal unitPrice){
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public BigDecimal getUnitPrice(){
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getManufacturer(){
		return manufacturer;
	}

	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public long getUnitsInStock(){
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock){
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder(){
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder){
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued(){
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued){
		this.discontinued = discontinued;
	}

	public String getCondition(){
		return condition;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	// "@XmlTransient" is for MarshallingView to exclude the field from xml.
	@XmlTransient
	public MultipartFile getProductImage(){
		return productImage;
	}

	public void setProductImage(MultipartFile productImage){
		this.productImage = productImage;
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof Product) {
			Product other = (Product)obj;

			return productId.equals(other.productId);
		}

		return false;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
}
