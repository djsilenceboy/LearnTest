
package com.djs.learn.javalang.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCachedThreadPool
{
	public void testExecutor(){
		int count = 5;
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer>[] futures = new Future[count];

		for (int i = 0; i < count; i++) {
			final Integer j = i;
			futures[i] = service.submit(() -> {
				System.out.println("Thread: " + j);
				return j;
			});
		}

		// The get() will only return when thread finished.
		for (int i = 0; i < count; i++) {
			try {
				Integer number = futures[i].get();

				System.out.println("Number: " + number);
			} catch (Exception e) {
				System.out.println("Number " + i + ": " + e);
			}
		}

		service.shutdown();
	}

	public static void main(String[] args){
		TestCachedThreadPool test = new TestCachedThreadPool();

		System.out.println("============================================================");
		test.testExecutor();
	}
}
