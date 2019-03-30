
package com.djs.learn.creational.builder;

import com.djs.learn.creational.common.ProductA;
import com.djs.learn.creational.common.ProductB;
import com.djs.learn.creational.common.ProductInterface;

public class BuilderB extends AbstractBuilder
{
	public BuilderB(){
		builderType = BuilderType.BuilderTypeB;
		name = BuilderB.class.getSimpleName();

		System.out.println("Create builder: " + name);
	}

	@Override
	public ProductInterface createPartA(){
		ProductInterface product = new ProductB();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}

	@Override
	public ProductInterface createPartB(){
		ProductInterface product = new ProductA();

		System.out.println("Create: " + product.getType() + " / " + product.getName());

		return product;
	}
}
