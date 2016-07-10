
package dj.test.javalang.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCompletionService
{
	static class Product
	{
		private String id;
		private long startTime = 0;
		private long stopTime = 0;

		public String getId(){
			return id;
		}

		public void setId(String id){
			this.id = id;
		}

		public long getStartTime(){
			return startTime;
		}

		public void setStartTime(long startTime){
			this.startTime = startTime;
		}

		public long getStopTime(){
			return stopTime;
		}

		public void setStopTime(long stopTime){
			this.stopTime = stopTime;
		}

		@Override
		public String toString(){
			return "Product (" + id + ": " + startTime + " - " + stopTime + ")";
		}
	}

	static class SampleClient implements Callable<Product>
	{
		@Override
		public Product call() throws Exception{
			String logId = "[" + Thread.currentThread().getName() + "] ";

			long startTime = System.currentTimeMillis();
			System.out.println(logId + "Start at " + startTime);
			Random random = new Random();

			try {
				Thread.sleep(random.nextInt(20));
			} catch (Exception e) {
			}

			long stopTime = System.currentTimeMillis();
			System.out.println(logId + "Stop at  " + stopTime);

			Product product = new Product();
			product.setId(Thread.currentThread().getName());
			product.setStartTime(startTime);
			product.setStopTime(stopTime);

			return product;
		}
	}

	public static void main(String[] args){
		int count = 5;
		ExecutorService executor = Executors.newFixedThreadPool(count);
		ExecutorCompletionService<Product> service = new ExecutorCompletionService<Product>(executor);

		for (int i = 0; i < count; i++) {
			service.submit(new SampleClient());
		}

		Lock lock = new ReentrantLock();

		for (int i = 0; i < count; i++) {
			try {
				lock.lock();

				// Find next completed thread.
				Future<Product> future = service.take();

				Product product = future.get();

				System.out.println(product);

				lock.unlock();
			} catch (Exception e) {
			}
		}

		executor.shutdown();
	}
}
