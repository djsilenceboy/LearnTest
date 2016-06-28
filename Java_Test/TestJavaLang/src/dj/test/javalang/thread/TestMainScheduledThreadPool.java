
package dj.test.javalang.thread;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestMainScheduledThreadPool
{
	static class SampleClient implements Runnable
	{
		private int runCount = 0;
		private long lastStartTime = 0;
		private long lastStopTime = 0;

		public void run(){
			String logId = "<" + Thread.currentThread().getName() + ">[" + runCount++ + "] ";
			long startTime = System.currentTimeMillis();
			System.out.println(logId + "Start at " + startTime);
			Random random = new Random();

			try {
				Thread.sleep(random.nextInt(20));
			} catch (Exception e) {
			}

			long stopTime = System.currentTimeMillis();
			System.out.println(logId + "Stop at  " + stopTime);
			System.out.println(logId + "Run for  " + (stopTime - startTime + 1));

			if ((lastStartTime > 0) && (lastStopTime > 0)) {
				System.out.println(logId + "Rate  is " + (startTime - lastStartTime + 1));
				System.out.println(logId + "Delay is " + (startTime - lastStopTime + 1));
			}

			lastStartTime = startTime;
			lastStopTime = stopTime;
		}
	}

	public void testFixedRate(){
		long period = 200;
		System.out.println("Fixed rate is " + period);
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		final ScheduledFuture<?> schedulerFuture = scheduler.scheduleAtFixedRate(new SampleClient(), 1, period, TimeUnit.MILLISECONDS);

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}

		schedulerFuture.cancel(true);
	}

	public void testFixedDelay(){
		long period = 200;
		System.out.println("Fixed delay is " + period);
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		final ScheduledFuture<?> schedulerFuture = scheduler.scheduleWithFixedDelay(new SampleClient(), 1, period, TimeUnit.MILLISECONDS);

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}

		schedulerFuture.cancel(true);
	}

	public static void main(String[] args){
		TestMainScheduledThreadPool test = new TestMainScheduledThreadPool();

		System.out.println("************************************************************");
		test.testFixedRate();

		System.out.println("************************************************************");
		test.testFixedDelay();
	}
}
