
package com.djs.learn.structural.adapter;

public class Adaptee
{
	String name;
	short age;

	public String getName(){
		return name;
	}

	public void setName(String name){
		System.out.println("Adaptee: Name: " + name);

		this.name = name;
	}

	public short getAge(){
		return age;
	}

	public void setAge(short age){
		System.out.println("Adaptee: Age: " + age);

		this.age = age;
	}

	void process(){
		System.out.println("Adaptee: " + name + " / " + age);
	}
}
