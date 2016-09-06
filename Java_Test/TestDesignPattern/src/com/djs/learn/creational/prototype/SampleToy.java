
package com.djs.learn.creational.prototype;

public class SampleToy implements Cloneable
{
	protected String name;
	protected String type;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

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
