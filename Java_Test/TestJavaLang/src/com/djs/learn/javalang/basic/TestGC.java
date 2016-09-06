
package com.djs.learn.javalang.basic;

/**
 * <pre>
 * </pre>
 */
public class TestGC
{
	public void testInit(){
		try {
			SampleGC sample1 = new SampleGC("Tom");
			SampleGC sample2 = new SampleGC("Jerry");
			SampleGC sample3 = new SampleGC("Mary");

			System.out.println("--------------------");

			sample1 = null;
			System.out.println(System.currentTimeMillis() + ":gc");
			System.gc();

			Thread.sleep(100);

			sample2 = null;
			System.out.println(System.currentTimeMillis() + ":gc");
			System.gc();

			Thread.sleep(5000);
		} catch (Exception e) {

		}
	}

	public static void main(String[] args){
		TestGC testMain = new TestGC();

		System.out.println("============================================================");

		testMain.testInit();

		System.out.println("============================================================");
	}
}

class SampleGC
{
	private String name;

	public SampleGC(String name){
		System.out.println(System.currentTimeMillis() + ":" + Sample.class.getName() + ":constructor: name = " + name);

		this.name = name;
	}

	@Override
	protected void finalize() throws Throwable{
		System.out.println(System.currentTimeMillis() + ":" + Sample.class.getName() + ":finalize: name = " + name);

		super.finalize();
	}

}
