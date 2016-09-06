
package com.djs.learn.sorting;

import java.util.Arrays;

abstract public class AbstractSorting implements SortingInterface
{
	public Object swap(Object x, Object y){
		return x;
	}

	public void printData(Object data){
		Class<?> objClass = data.getClass();
		String temp;

		// Primitive array.
		if (objClass == boolean[].class) {
			temp = Arrays.toString((boolean[])data);
		} else if (objClass == byte[].class) {
			temp = Arrays.toString((byte[])data);
		} else if (objClass == char[].class) {
			temp = Arrays.toString((char[])data);
		} else if (objClass == double[].class) {
			temp = Arrays.toString((double[])data);
		} else if (objClass == float[].class) {
			temp = Arrays.toString((float[])data);
		} else if (objClass == int[].class) {
			temp = Arrays.toString((int[])data);
		} else if (objClass == long[].class) {
			temp = Arrays.toString((long[])data);
		} else if (objClass == short[].class) {
			temp = Arrays.toString((short[])data);
		}
		// Class array.
		else {
			temp = Arrays.toString((Object[])data);
		}

		System.out.println(temp);
	}

	public void printData(Integer[] data, int startIndex, int size){
		StringBuilder temp = new StringBuilder();

		temp.append("[");
		for (int i = startIndex; i < startIndex + size; i++) {
			temp.append(data[i]);

			if (i < startIndex + size - 1) {
				temp.append(", ");
			}
		}
		temp.append("]");

		System.out.println(temp);
	}
}
