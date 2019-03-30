
package com.djs.learn.javalang.concurrency;

public class TestDeadLock3
{
	static class SampleClient implements Runnable
	{
		private Object lockA;
		private Object lockB;

		public SampleClient(Object lockA, Object lockB){
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

					Thread.sleep(500);

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

		String lock1 = "Lock1";
		String lock2 = "Lock2";

		Thread[] threads = new Thread[]{new Thread(new SampleClient(lock1, lock2)), new Thread(new SampleClient(lock2, lock1))};

		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}
}
