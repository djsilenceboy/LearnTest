
package com.djs.learn.creational.builder;

abstract class AbstractBuilder implements BuilderInterface
{
	BuilderType builderType;
	String name;

	@Override
	public BuilderType getType(){
		return builderType;
	}

	@Override
	public String getName(){
		return name;
	}
}
