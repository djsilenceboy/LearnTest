
package com.djs.learn.javalang.concurrency;

import java.util.concurrent.CountDownLatch;

public class TestDeadLock
{
	static class SampleClient implements Runnable
	{
		private CountDownLatch countDownA;
		private CountDownLatch countDownB;
		private Object lockA;
		private Object lockB;

		public SampleClient(CountDownLatch countDownA, CountDownLatch countDownB, Object lockA, Object lockB){
			this.countDownA = countDownA;
			this.countDownB = countDownB;
			this.lockA = lockA;
			this.lockB = lockB;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			try {
				System.out.println(logId + "Try get " + lockA);

				synchronized (lockA) {
					System.out.println(logId + "Get " + lockA);

					countDownA.countDown();
					countDownB.await();

					System.out.println(logId + "Try get " + lockB);

					synchronized (lockB) {
						System.out.println(logId + "Get " + lockB);
					}
				}

			} catch (Exception e) {

			}

			System.out.println(logId + "Stop.");
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		CountDownLatch countDownA = new CountDownLatch(2);
		CountDownLatch countDownB = new CountDownLatch(1);
		String lock1 = "Lock1";
		String lock2 = "Lock2";

		Thread[] threads =
		                   new Thread[]{new Thread(new SampleClient(countDownA, countDownB, lock1, lock2)),
		                                new Thread(new SampleClient(countDownA, countDownB, lock2, lock1))};

		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}

		try {
			countDownA.await();
			countDownB.countDown();
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}
}
