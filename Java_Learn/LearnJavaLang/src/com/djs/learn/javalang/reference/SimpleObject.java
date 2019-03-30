
package com.djs.learn.javalang.reference;

public class SimpleObject implements Cloneable
{
	private String info = null;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return info;
	}
}
