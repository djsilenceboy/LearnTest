
package dj.test.creational.abstractFactory;

import dj.test.creational.common.ProductB;
import dj.test.creational.common.ProductInterface;
import dj.test.creational.common.ProductType;

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
