
package com.djs.learn.javalang.basic;

// The enum is "static final" by default.
public enum SampleWeek
{
	Monday(10) {
		@Override
		public String getActor(){
			return "Tom";
		}
	},

	Tuesday(20) {
		@Override
		public String getActor(){
			return "Jerry";
		}
	};

	private int count;

	SampleWeek(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public abstract String getActor();
}
