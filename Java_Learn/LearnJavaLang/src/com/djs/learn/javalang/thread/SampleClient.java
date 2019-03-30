
package com.djs.learn.javalang.thread;

import java.util.Random;
import java.util.concurrent.Callable;

public class SampleClient implements Callable<Product>
{
	@Override
	public Product call() throws Exception{
		String logId = "[" + Thread.currentThread().getName() + "#" + Thread.currentThread().getId() + "] ";

		long startTime = System.currentTimeMillis();
		System.out.println(logId + "Start at " + startTime);
		Random random = new Random();

		try {
			Thread.sleep(random.nextInt(2000));
		} catch (Exception e) {
		}

		long stopTime = System.currentTimeMillis();
		System.out.println(logId + "Stop at  " + stopTime);

		Product product = new Product();
		product.setId(Thread.currentThread().getName() + "#" + Thread.currentThread().getId());
		product.setStartTime(startTime);
		product.setStopTime(stopTime);

		return product;
	}
}
