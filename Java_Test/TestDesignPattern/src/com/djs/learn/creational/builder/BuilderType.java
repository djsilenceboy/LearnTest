
package com.djs.learn.creational.builder;

public enum BuilderType
{
	BuilderTypeA("Builder type A"), BuilderTypeB("Builder type B");

	String name;

	BuilderType(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
