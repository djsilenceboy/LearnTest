
package com.djs.learn.common;

import java.util.Arrays;

public class Utils
{
	public static String arrayToHexString(Object obj){
		Class<?> objClass = obj.getClass();
		StringBuilder temp = new StringBuilder();

		temp.append("[");

		if (objClass == byte[].class) {
			for (byte b : ((byte[])obj)) {
				temp.append(String.format("%02X, ", b));
			}
		} else if (objClass == char[].class) {
			for (char c : ((char[])obj)) {
				temp.append(String.format("%04X, ", (short)c));
			}
		} else if (objClass == short[].class) {
			for (short s : ((short[])obj)) {
				temp.append(String.format("%04X, ", s));
			}
		} else if (objClass == int[].class) {
			for (int i : ((int[])obj)) {
				temp.append(String.format("%08X, ", i));
			}
		} else if (objClass == long[].class) {
			for (long l : ((long[])obj)) {
				temp.append(String.format("%16X, ", l));
			}
		} else {
			temp.append(Arrays.toString((Object[])obj));
		}

		temp.append("]");

		return temp.toString();
	}

	public static String byteToBinString(byte data){
		// The purpose of "& 0xFF" is to trim leading negative sign while converting to int.
		String bin = String.format("%8s", Integer.toBinaryString(data & 0xFF)).replace(' ', '0');

		return bin;
	}

	public static String intToBinString(int data){
		String bin = String.format("%32s", Integer.toBinaryString(data)).replace(' ', '0');

		return bin;
	}
}
