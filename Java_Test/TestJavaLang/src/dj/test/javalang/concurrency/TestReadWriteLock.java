
package dj.test.javalang.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock
{
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void read(int num){
		Lock lock = readWriteLock.readLock();

		try {
			lock.lock();
			System.out.println("<Read>[" + num + "] Lock");
			Thread.sleep(10);
		} catch (InterruptedException e) {
		} finally {
			System.out.println("<Read>[" + num + "] Unlock");
			lock.unlock();
		}
	}

	public void write(int num){
		Lock lock = readWriteLock.writeLock();

		try {
			lock.lock();
			System.out.println("<Write>[" + num + "] Lock");
			Thread.sleep(100);
		} catch (InterruptedException e) {
		} finally {
			System.out.println("<Write>[" + num + "] Unlock");
			lock.unlock();
		}
	}

	public static void main(String[] args){
		TestReadWriteLock test = new TestReadWriteLock();

		ExecutorService service = Executors.newFixedThreadPool(8);

		try {
			for (int i = 0; i < 50; i++) {
				final int k = i;
				service.submit(() -> test.read(k));

				if (i % 10 == 0) {
					service.submit(() -> test.write(1000 + k));
				}
			}
		} finally {
			service.shutdown();
		}
	}
}
