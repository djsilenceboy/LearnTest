
package com.djs.learn.creational.common;

public enum ProductType
{
	ProductTypeA("Product type A"), ProductTypeB("Product type B");

	String name;

	ProductType(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
