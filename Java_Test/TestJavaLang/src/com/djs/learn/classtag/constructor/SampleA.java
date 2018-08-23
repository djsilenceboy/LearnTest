
package com.djs.learn.classtag.constructor;

public class SampleA
{
	private SampleA(){
		System.out.println("SampleA()");
	}

	public static void main(String[] args){
		SampleA test = new SampleA();
	}
}
