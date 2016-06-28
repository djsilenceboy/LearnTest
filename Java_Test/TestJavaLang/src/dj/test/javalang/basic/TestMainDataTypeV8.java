
package dj.test.javalang.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMainDataTypeV8
{
	public void testArray(){
		System.out.println("Test: Array");

		List<String> list = new ArrayList<>();

		// This will fail.
		list.addAll(new ArrayList<>());
		list.addAll(Arrays.asList());
	}

	public static void main(String[] args){
		TestMainDataTypeV8 test = new TestMainDataTypeV8();

		System.out.println("============================================================");

		test.testArray();

		System.out.println("============================================================");
	}
}
