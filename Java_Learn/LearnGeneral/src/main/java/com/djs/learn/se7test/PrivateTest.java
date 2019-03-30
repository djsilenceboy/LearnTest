
package com.djs.learn.se7test;

public class PrivateTest
{
	public static void main(String[] args){
		PrivateTest test = new PrivateTest();

		test.show();

		SampleA<String, Integer> ref = new SampleA<>("aString", 5);
		ref.print();
	}

	private void show(){
	};
}

class SampleA<T, U>
{
	private T t;
	private U u;

	SampleA(T myT, U myU){
		t = myT;
		u = myU;
	}

	public void print(){
		System.out.println(t);
		System.out.println(u);
	}
}
