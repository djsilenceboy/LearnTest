
package com.djs.learn.creational.builder;

import com.djs.learn.creational.common.ProductInterface;

public interface BuilderInterface
{
	BuilderType getType();

	String getName();

	ProductInterface createPartA();

	ProductInterface createPartB();
}
