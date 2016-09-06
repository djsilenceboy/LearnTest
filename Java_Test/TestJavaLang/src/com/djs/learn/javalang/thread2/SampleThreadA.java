
package com.djs.learn.javalang.thread2;

public class SampleThreadA extends Thread
{
	private long sleepTime;
	String logId;

	public SampleThreadA(long sleepTime){
		this.sleepTime = sleepTime;
	}

	@Override
	public void run(){
		logId = "[" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "Enter... ");
		System.out.println(logId + "Sleep time = " + sleepTime);

		// testSequence();
		testSleep();

		System.out.println(logId + "Leave... ");
	}

	public void testSequence(){
		int i;
		int max = 1000;

		try {
			for (i = 0; i < max; i++) {
				System.out.println(logId + i);
				Thread.sleep(sleepTime);
			}
		} catch (Exception e) {

		}
	}

	public void testSleep(){
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {

		}
	}
}
