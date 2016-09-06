
package com.djs.learn.structural.adapter;

public class Adapter implements AdapterInterface
{
	Adaptee adaptee;

	public Adapter(Adaptee adaptee){
		this.adaptee = adaptee;
	}

	@Override
	public void setName(String name){
		System.out.println("Adapter: Name: " + name);

		adaptee.setName("#" + name + "#");
	}

	@Override
	public void setAge(int age){
		System.out.println("Adapter: Age: " + age);

		adaptee.setAge((short)age);
	}

	@Override
	public void process(){
		System.out.println("Adapter: Process.");

		adaptee.process();
	}
}
