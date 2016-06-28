
package dj.test.javalang.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBarrier
{
	static class Product
	{
		private int sellerId;

		public int getSellerId(){
			return sellerId;
		}

		public void setSellerId(int sellerId){
			this.sellerId = sellerId;
		}

		@Override
		public String toString(){
			return "Product (Seller " + sellerId + ")";
		}
	}

	static class Seller implements Runnable
	{
		private int id;
		private BlockingQueue<Product> queue;
		private CyclicBarrier barrier;
		private String logId;

		public Seller(int id, BlockingQueue<Product> queue, CyclicBarrier barrier){
			this.id = id;
			this.queue = queue;
			this.barrier = barrier;

			logId = "Seller <" + id + "> ";
		}

		public void run(){
			System.out.println(logId + "Start.");

			Product product = new Product();
			product.setSellerId(id);

			System.out.println(logId + product);

			queue.add(product);

			try {
				barrier.await();
			} catch (Exception e) {
			}

			System.out.println(logId + "Stop.");
		}
	}

	static class Buyer implements Runnable
	{
		private BlockingQueue<Product> queue;
		private String logId;

		public Buyer(BlockingQueue<Product> queue){
			this.queue = queue;

			logId = "Buyer ";
		}

		public void run(){
			System.out.println(logId + "Start.");

			while (!queue.isEmpty()) {
				try {
					Product product = queue.take();

					System.out.println(logId + product);
				} catch (Exception e) {
				}
			}

			System.out.println(logId + "Stop.");
		}
	}

	public static void main(String[] args){
		int sellerCount = 10;
		BlockingQueue<Product> queue = new LinkedBlockingQueue<Product>();
		CyclicBarrier barrier = new CyclicBarrier(sellerCount, new Buyer(queue) {});

		for (int i = 1; i <= sellerCount; i++) {
			Thread thread = new Thread(new Seller(i, queue, barrier));
			thread.setDaemon(true);
			thread.start();
		}

		// All sellers will only stop after buyer stopped.

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
}
