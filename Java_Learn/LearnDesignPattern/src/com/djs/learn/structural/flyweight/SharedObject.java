
package com.djs.learn.structural.flyweight;

public class SharedObject
{
	String name;

	public SharedObject(String name){
		System.out.println("Create SharedObject: " + name);

		this.name = name;
	}

	public String getName(){
		return name;
	}
}
