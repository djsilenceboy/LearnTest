
package dj.test.creational.abstractFactory;

import dj.test.creational.common.ProductA;
import dj.test.creational.common.ProductInterface;
import dj.test.creational.common.ProductType;

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
