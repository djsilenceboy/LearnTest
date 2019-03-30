
package com.djs.learn.creational.factory;

import com.djs.learn.creational.common.ProductA;
import com.djs.learn.creational.common.ProductB;
import com.djs.learn.creational.common.ProductInterface;
import com.djs.learn.creational.common.ProductType;

public class Factory
{
	public ProductInterface create(ProductType productType){
		System.out.println("Create product type: " + productType.getName());

		if (productType == ProductType.ProductTypeA) return new ProductA();
		else if (productType == ProductType.ProductTypeB) return new ProductB();
		else return null;
	}
}
