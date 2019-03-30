
package com.djs.learn.creational.abstractFactory;

import com.djs.learn.creational.common.ProductB;
import com.djs.learn.creational.common.ProductInterface;
import com.djs.learn.creational.common.ProductType;

public class ProductTypeBFactory extends AbstractProductTypeFactory
{
	public ProductTypeBFactory(){
		productType = ProductType.ProductTypeB;
		name = ProductTypeBFactory.class.getSimpleName();

		System.out.println("Create factory: " + name);
	}

	@Override
	public ProductInterface create(){
		ProductInterface product = new ProductB();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}
}
