
package com.djs.learn.javalang.concurrency;

public class TestSync
{
	static class SampleClient implements Runnable
	{
		private Object lock;

		public SampleClient(Object lock){
			this.lock = lock;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			while (true) {
				synchronized (lock) {
					System.out.println(logId + "Get lock.");
					try {
						// If not add lock.notify()/wait(), only one thread can get lock each time.
						// If add lock.notify()/wait(), it actually releases the lock. The all other threads can get lock.
						lock.notify();
						lock.wait();
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}

				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		Object lock = new Object();
		int count = 4;

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread(new SampleClient(lock));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
}
