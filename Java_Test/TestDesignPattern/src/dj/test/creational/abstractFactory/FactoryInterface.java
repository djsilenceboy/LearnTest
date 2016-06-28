
package dj.test.creational.abstractFactory;

import dj.test.creational.common.ProductInterface;
import dj.test.creational.common.ProductType;

public interface FactoryInterface
{
	ProductType getType();

	String getName();

	ProductInterface create();
}
