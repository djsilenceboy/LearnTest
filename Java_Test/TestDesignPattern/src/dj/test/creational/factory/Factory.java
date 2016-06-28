
package dj.test.creational.factory;

import dj.test.creational.common.ProductA;
import dj.test.creational.common.ProductB;
import dj.test.creational.common.ProductInterface;
import dj.test.creational.common.ProductType;

public class Factory
{
	public ProductInterface create(ProductType productType){
		System.out.println("Create product type: " + productType.getName());

		if (productType == ProductType.ProductTypeA) return new ProductA();
		else if (productType == ProductType.ProductTypeB) return new ProductB();
		else return null;
	}
}
