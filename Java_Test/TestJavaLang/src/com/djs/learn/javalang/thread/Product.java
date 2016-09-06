
package com.djs.learn.javalang.thread;

public class Product
{
	private String id;
	private long startTime = 0;
	private long stopTime = 0;

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public long getStartTime(){
		return startTime;
	}

	public void setStartTime(long startTime){
		this.startTime = startTime;
	}

	public long getStopTime(){
		return stopTime;
	}

	public void setStopTime(long stopTime){
		this.stopTime = stopTime;
	}

	@Override
	public String toString(){
		return "Product (" + id + ": " + startTime + " - " + stopTime + ")";
	}
}
