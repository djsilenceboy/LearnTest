
package com.djs.learn.mvc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.djs.learn.mvc.validator.ProductId;
import com.fasterxml.jackson.annotation.JsonIgnore;

// "@XmlRootElement" is for MarshallingView.
// javax.validation.constraints classes, such as Pattern, Size, Min, is for JSR-303/Bean Validation.
// ProductId annotation is for customized JSR-303/Bean Validation.
@XmlRootElement
public class Product implements Serializable
{
	private static final long serialVersionUID = 3678107792576131001L;

	@Pattern(regexp = "P[1-9]+", message = "{Pattern.Product.productId.validation}")
	@ProductId
	private String productId;

	@Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
	private String name;

	@Min(value = 0, message = "{Min.Product.unitPrice.validation}")
	@Digits(integer = 8, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
	@NotNull(message = "{NotNull.Product.unitPrice.validation}")
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

			if (productId != null) return productId.equals(other.productId);
		}

		return false;
	}

	@Override
	public int hashCode(){
		return (productId == null) ? 0 : productId.hashCode();
	}

	@Override
	public String toString(){
		return "Product [productId=" + productId + ", name=" + name + ", unitPrice=" + unitPrice + ", description=" + description + ", manufacturer="
		        + manufacturer + ", category=" + category + ", unitsInStock=" + unitsInStock + ", unitsInOrder=" + unitsInOrder + ", discontinued="
		        + discontinued + ", condition=" + condition + "]";
	}
}
