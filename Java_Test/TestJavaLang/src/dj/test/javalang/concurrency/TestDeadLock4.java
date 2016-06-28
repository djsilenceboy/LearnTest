
package dj.test.javalang.concurrency;

import java.util.concurrent.CyclicBarrier;

public class TestDeadLock4
{
	static class SampleClient implements Runnable
	{
		private CyclicBarrier countA;
		private CyclicBarrier countB;
		private Object lockA;
		private Object lockB;

		public SampleClient(CyclicBarrier countA, CyclicBarrier countB, Object lockA, Object lockB){
			this.countA = countA;
			this.countB = countB;
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

					countB.await();

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

		CyclicBarrier countA = new CyclicBarrier(2);
		CyclicBarrier countB = new CyclicBarrier(1);
		String lock1 = "Lock1";
		String lock2 = "Lock2";

		Thread[] threads = new Thread[]{new Thread(new SampleClient(countA, countB, lock1, lock2)), new Thread(new SampleClient(countA, countB, lock2, lock1))};

		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}

		try {
			countA.await();
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}
}
