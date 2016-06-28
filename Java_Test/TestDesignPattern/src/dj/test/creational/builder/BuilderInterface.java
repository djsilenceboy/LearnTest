
package dj.test.creational.builder;

import dj.test.creational.common.ProductInterface;

public interface BuilderInterface
{
	BuilderType getType();

	String getName();

	ProductInterface createPartA();

	ProductInterface createPartB();
}
