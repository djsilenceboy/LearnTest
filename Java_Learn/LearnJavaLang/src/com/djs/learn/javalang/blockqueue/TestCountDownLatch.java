
package com.djs.learn.javalang.blockqueue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch
{
	static class Buyer implements Runnable
	{
		private int id;
		private CountDownLatch buyerCountDown;
		private CountDownLatch toBuyCountDown;
		private Semaphore semaphore;
		private String logId;

		public Buyer(int id, CountDownLatch buyerCountDown, CountDownLatch toBuyCountDown, Semaphore semaphore){
			this.id = id;
			this.buyerCountDown = buyerCountDown;
			this.toBuyCountDown = toBuyCountDown;
			this.semaphore = semaphore;

			logId = "Buyer <" + id + "> ";
		}

		public void run(){
			System.out.println(logId + "Ready.");

			try {
				buyerCountDown.countDown();
				toBuyCountDown.await();

				if (semaphore.tryAcquire(20, TimeUnit.MILLISECONDS)) {
					System.out.println(logId + "get product.");
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args){
		int productCount = 2;
		int buyerCount = 10;
		CountDownLatch buyerCountDown = new CountDownLatch(buyerCount);
		CountDownLatch toBuyCountDown = new CountDownLatch(1);
		Semaphore semaphore = new Semaphore(productCount, true);

		for (int i = 1; i <= buyerCount; i++) {
			Thread thread = new Thread(new Buyer(i, buyerCountDown, toBuyCountDown, semaphore));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			buyerCountDown.await();
			System.out.println("All buyers ready.");
			System.out.println("Product available now.");
			toBuyCountDown.countDown();

			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
}
