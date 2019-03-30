
package com.djs.learn.creational.common;


public class ProductA extends AbstractProduct
{
	public ProductA(){
		productType = ProductType.ProductTypeA;
		name = ProductA.class.getSimpleName();

		System.out.println("Create product: " + name);
	}
}
