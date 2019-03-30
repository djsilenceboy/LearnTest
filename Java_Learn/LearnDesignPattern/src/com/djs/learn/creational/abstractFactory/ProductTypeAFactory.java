
package com.djs.learn.creational.abstractFactory;

import com.djs.learn.creational.common.ProductA;
import com.djs.learn.creational.common.ProductInterface;
import com.djs.learn.creational.common.ProductType;

public class ProductTypeAFactory extends AbstractProductTypeFactory
{
	public ProductTypeAFactory(){
		productType = ProductType.ProductTypeA;
		name = ProductTypeAFactory.class.getSimpleName();

		System.out.println("Create factory: " + name);
	}

	@Override
	public ProductInterface create(){
		ProductInterface product = new ProductA();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}
}
