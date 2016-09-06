
package com.djs.learn.creational.abstractFactory;

import com.djs.learn.creational.common.ProductInterface;
import com.djs.learn.creational.common.ProductType;

public interface FactoryInterface
{
	ProductType getType();

	String getName();

	ProductInterface create();
}
