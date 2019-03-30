
package com.djs.learn.creational.abstractFactory;

import com.djs.learn.creational.common.ProductType;

abstract class AbstractProductTypeFactory implements FactoryInterface
{
	ProductType productType;
	String name;

	@Override
	public ProductType getType(){
		return productType;
	}

	@Override
	public String getName(){
		return name;
	}
}
