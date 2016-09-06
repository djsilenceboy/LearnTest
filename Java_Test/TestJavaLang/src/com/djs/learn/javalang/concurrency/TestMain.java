
package com.djs.learn.javalang.concurrency;

public class TestMain
{
	public void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	public void test(){
		SampleResourceInterface[] sampleResources =
		                                            new SampleResourceInterface[]{new SampleResourceA(), new SampleResourceB(), new SampleResourceC1(),
		                                                                          new SampleResourceC2(), new SampleResourceD()};

		for (SampleResourceInterface sampleResource : sampleResources) {
			System.out.println("Test " + sampleResource.getClass().getName());

			SampleThread[] sampleThreads =
			                               new SampleThread[]{new SampleThread(sampleResource, 0, 5), new SampleThread(sampleResource, 1, 5),
			                                                  new SampleThread(sampleResource, 2, 5)};

			for (SampleThread sampleThread : sampleThreads) {
				sampleThread.start();
			}

			sleep(1000);
			System.out.println("Last count = " + sampleResource.getCurrentCount());
			System.out.println("************************************************************");
		}
	}

	public static void main(String[] args){
		TestMain testMain = new TestMain();

		testMain.test();
	}
}
