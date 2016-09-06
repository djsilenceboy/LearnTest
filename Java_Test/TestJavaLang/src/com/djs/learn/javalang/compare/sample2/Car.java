
package com.djs.learn.javalang.compare.sample2;

public class Car
{
	public String name = "car";

	public Car(){
	}

	public Car(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return "name = " + name;
	}
}
