
package dj.test.javalang.methods;

import java.util.Arrays;

public class SampleMethod
{
	public SampleMethod(){
		System.out.println(SampleMethod.class.getName() + ":SampleMethod()");
	}

	public void SampleMethod(){
		System.out.println(SampleMethod.class.getName() + ":void SampleMethod()");
	}

	public void getStringList(String... names){
		System.out.println(SampleMethod.class.getName() + ":getStringList: " + names.length);
		System.out.println(SampleMethod.class.getName() + ":getStringList: " + Arrays.toString(names));
	}

	public void getNumbers(byte... data){
		System.out.println(SampleMethod.class.getName() + ":getNumbers(byte): " + data.length);
		System.out.println(SampleMethod.class.getName() + ":getNumbers(byte): " + Arrays.toString(data));
	}

	public void getNumbers(int... data){
		System.out.println(SampleMethod.class.getName() + ":getNumbers(int): " + data.length);
		System.out.println(SampleMethod.class.getName() + ":getNumbers(int): " + Arrays.toString(data));
	}

	public void getNumbers(double... data){
		System.out.println(SampleMethod.class.getName() + ":getNumbers(double): " + data.length);
		System.out.println(SampleMethod.class.getName() + ":getNumbers(double): " + Arrays.toString(data));
	}

	public static void testName(){
		StackTraceElement[] traceList = new Throwable().getStackTrace();

		for (StackTraceElement item : traceList) {

			System.out.println(item.getFileName() + ": " + item.getClassName() + "." + item.getMethodName() + "(line " + item.getLineNumber() + "): ");
		}
	}

	public static void testName2(){
		String name = (new Throwable().getStackTrace())[0].getMethodName();

		System.out.println(name);
	}

	public void testName3(){
		String name = (new Throwable().getStackTrace())[0].getMethodName();

		System.out.println(name);
	}
}
