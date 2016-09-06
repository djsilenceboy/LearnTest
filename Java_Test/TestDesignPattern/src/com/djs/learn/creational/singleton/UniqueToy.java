
package com.djs.learn.creational.singleton;

public class UniqueToy
{
	protected String name;
	protected String type;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	@Override
	public String toString(){
		return "SampleToy [name=" + name + ", type=" + type + "]";
	}
}
