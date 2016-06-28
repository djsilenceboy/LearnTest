
package dj.test.javalang.methods;

public class TestMain
{
	public static void main(String[] args){
		SampleMethod sampleMethod = new SampleMethod();

		sampleMethod.SampleMethod();

		System.out.println("============================================================");

		// "null" will cause NullPointerException.
		// sampleMethod.getStringList(null);

		sampleMethod.getStringList();

		sampleMethod.getStringList("Tom", "Jerry", "Mary");

		System.out.println("============================================================");

		sampleMethod.getNumbers();

		sampleMethod.getNumbers((byte)10, (byte)20, (byte)30);

		sampleMethod.getNumbers(10, 20, 30);

		sampleMethod.getNumbers(new int[]{10, 20, 30});

		sampleMethod.getNumbers(10, 20.0, 30);

		sampleMethod.getNumbers(10.1, 20.2, 30.3);

		System.out.println("============================================================");

		SampleMethod.testName();
		SampleMethod.testName2();

		sampleMethod.testName();
		sampleMethod.testName2();
		sampleMethod.testName3();
	}
}
