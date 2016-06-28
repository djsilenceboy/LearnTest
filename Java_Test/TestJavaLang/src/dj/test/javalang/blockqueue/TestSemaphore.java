
package dj.test.javalang.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore
{
	static class Seller
	{
		private int id;
		private BlockingQueue<Seller> queue;
		private Semaphore semaphore;
		private String logId;

		public Seller(int id, BlockingQueue<Seller> queue, Semaphore semaphore){
			this.id = id;
			this.queue = queue;
			this.semaphore = semaphore;

			logId = "Seller <" + id + "> ";
		}

		public void serve(int buyerId){
			System.out.println(logId + "start serveing Buyer <" + buyerId + ">");

			try {
				Thread.sleep((long)(Math.random() * 10));
			} catch (Exception e) {
			}

			queue.add(this);
			System.out.println(logId + "stop serveing Buyer <" + buyerId + ">");
			semaphore.release();
		}
	}

	static class Buyer implements Runnable
	{
		private int id;
		private BlockingQueue<Seller> queue;
		private Semaphore semaphore;
		private String logId;

		public Buyer(int id, BlockingQueue<Seller> queue, Semaphore semaphore){
			this.id = id;
			this.queue = queue;
			this.semaphore = semaphore;

			logId = "Buyer <" + id + "> ";
		}

		public void run(){
			System.out.println(logId + "Start.");

			try {
				if (semaphore.tryAcquire(20, TimeUnit.MILLISECONDS)) {
					queue.take().serve(id);
				} else {
					System.out.println(logId + "not served.");
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args){
		int sellerCount = 2;
		BlockingQueue<Seller> queue = new LinkedBlockingQueue<Seller>();
		Semaphore semaphore = new Semaphore(sellerCount, true);

		for (int i = 1; i <= sellerCount; i++) {
			queue.add(new Seller(i, queue, semaphore));
		}

		for (int i = 1; i <= 20; i++) {
			Thread thread = new Thread(new Buyer(i, queue, semaphore));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}
}
