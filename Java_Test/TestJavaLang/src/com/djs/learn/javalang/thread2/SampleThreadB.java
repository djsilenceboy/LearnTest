
package com.djs.learn.javalang.thread2;

public class SampleThreadB extends Thread
{
	private TestMain tt;
	private long sleepTime;
	String logId;

	public SampleThreadB(TestMain tt, long sleepTime){
		this.tt = tt;
		this.sleepTime = sleepTime;

		System.out.println("Sleep time = " + sleepTime);
	}

	@Override
	public void run(){
		logId = "[" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "Enter... ");
		System.out.println(logId + "Sleep time = " + sleepTime);

		// testSequence();
		testSleep();

		System.out.println(logId + "Leave... ");

		tt.threadlist.remove(this);
	}

	public void testSleep(){
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {

		}
	}
}
