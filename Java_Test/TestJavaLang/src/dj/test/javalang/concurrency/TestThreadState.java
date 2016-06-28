
package dj.test.javalang.concurrency;

import java.util.ArrayList;
import java.util.List;

public class TestThreadState
{
	static class SampleClient implements Runnable
	{
		private Object lock;
		private String keyword;

		public SampleClient(Object lock, String keyword){
			this.lock = lock;
			this.keyword = keyword;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			while (true) {
				synchronized (lock) {
					try {
						System.out.println(logId + keyword);
						lock.notify();
						lock.wait();
						Thread.sleep(50);
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		List<Thread> threads = new ArrayList<Thread>();
		Object lock = new Object();
		int count = 2;

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread(new SampleClient(lock, "Num-" + i));
			thread.setDaemon(true);
			thread.start();
			threads.add(thread);
		}

		int totalSleepTime = 500;
		int sliceTime = 10;

		while (totalSleepTime > 0) {
			for (Thread thread : threads) {
				System.out.println(logId + thread.getName() + ": " + thread.getState());
			}

			try {
				Thread.sleep(sliceTime);
			} catch (Exception e) {
			}

			totalSleepTime -= sliceTime;
		}
	}
}
