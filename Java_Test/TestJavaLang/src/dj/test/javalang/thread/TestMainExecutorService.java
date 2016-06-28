
package dj.test.javalang.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TestMainExecutorService
{
	public void testExecutor(){
		int count = 5;
		ExecutorService service = Executors.newFixedThreadPool(count);
		Future<Product>[] futures = new Future[count];

		for (int i = 0; i < count; i++) {
			futures[i] = service.submit(new SampleClient());
		}

		futures[0].cancel(true);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		// The get() will only return when thread finished.
		for (int i = 0; i < count; i++) {
			try {
				Product product = futures[i].get();

				System.out.println(product);
			} catch (Exception e) {
				System.out.println("Product " + i + ": " + e);
			}
		}

		service.shutdown();
	}

	public void testFutureTask(){
		FutureTask<Product> future = new FutureTask<Product>(new SampleClient());
		future.run();

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		// The get() will only return when thread finished.
		try {
			Product product = future.get();

			System.out.println(product);
		} catch (Exception e) {
			System.out.println("Product " + ": " + e);
		}
	}

	public static void main(String[] args){
		TestMainExecutorService test = new TestMainExecutorService();

		System.out.println("============================================================");
		test.testExecutor();

		System.out.println("============================================================");
		test.testFutureTask();
	}
}
