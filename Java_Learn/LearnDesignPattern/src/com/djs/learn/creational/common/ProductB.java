
package com.djs.learn.creational.common;


public class ProductB extends AbstractProduct
{
	public ProductB(){
		productType = ProductType.ProductTypeB;
		name = ProductB.class.getSimpleName();

		System.out.println("Create product: " + name);
	}
}
