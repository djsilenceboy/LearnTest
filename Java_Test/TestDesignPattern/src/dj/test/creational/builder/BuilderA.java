
package dj.test.creational.builder;

import dj.test.creational.common.ProductA;
import dj.test.creational.common.ProductB;
import dj.test.creational.common.ProductInterface;

public class BuilderA extends AbstractBuilder
{
	public BuilderA(){
		builderType = BuilderType.BuilderTypeA;
		name = BuilderA.class.getSimpleName();

		System.out.println("Create builder: " + name);
	}

	@Override
	public ProductInterface createPartA(){
		ProductInterface product = new ProductA();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}

	@Override
	public ProductInterface createPartB(){
		ProductInterface product = new ProductB();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}
}
