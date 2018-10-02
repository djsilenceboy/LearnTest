
package com.djs.learn.javalang.basic;

/**
 * <pre>
============================================================
1538466981026:com.djs.learn.javalang.basic.Sample:constructor: name = Tom
1538466981026:com.djs.learn.javalang.basic.Sample:constructor: name = Jerry
1538466981026:com.djs.learn.javalang.basic.Sample:constructor: name = Mary
--------------------
1538466981026:gc
1538466981042:com.djs.learn.javalang.basic.Sample:finalize: name = Tom
1538466981152:gc
1538466981152:com.djs.learn.javalang.basic.Sample:finalize: name = Jerry
============================================================
 * </pre>
 *
 * gc() may not run even called.
 * finalize() may not be called when application finished.
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
