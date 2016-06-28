
package dj.test.creational.abstractFactory;

import dj.test.creational.common.ProductType;

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
