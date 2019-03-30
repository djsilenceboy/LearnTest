
package com.djs.learn.javalang.generics;

public class TestGenericStack
{
	public class NumberStack<T extends Number>
	{
		Number[] stack = new Number[100];

		public void setStack(T[] stack){
			this.stack = stack;
		}
	}

	public class ObjectStack<T>
	{
		// Cannot new unknown T.
		// T[] stack = new T[100];

		T[] stack;

		public ObjectStack(T[] stack){
			this.stack = stack;
		}
	}
}
