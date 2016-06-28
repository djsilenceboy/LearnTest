
package dj.test.javalang.concurrency;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock
{
	static class SampleLock implements Runnable
	{
		private final Lock lock = new ReentrantLock();
		private Lock friendLock;

		public Lock getLock(){
			return lock;
		}

		public void setFriendLock(Lock friendLock){
			this.friendLock = friendLock;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			Random random = new Random();

			while (true) {
				boolean locked = false;
				boolean friendLocked = false;

				try {
					locked = lock.tryLock();

					if (locked) {
						friendLocked = friendLock.tryLock();

						if (friendLocked) {
							System.out.println(logId + "Get both locks.");
						} else {
							System.out.println(logId + "--> Not get friend lock.");
						}
					} else {
						System.out.println(logId + "--> Not get own lock.");
					}
				} catch (Exception e) {
				} finally {
					if (locked) {
						lock.unlock();
						System.out.println(logId + "Release own locks.");
					}

					if (friendLocked) {
						friendLock.unlock();
						System.out.println(logId + "Release friend locks.");
					}
				}

				try {
					Thread.sleep(random.nextInt(10));
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String[] args){
		int count = 2;
		SampleLock[] sampleLocks = new SampleLock[count];

		for (int i = 0; i < count; i++) {
			sampleLocks[i] = new SampleLock();
		}

		sampleLocks[0].setFriendLock(sampleLocks[1].getLock());
		sampleLocks[1].setFriendLock(sampleLocks[0].getLock());

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread(sampleLocks[i]);
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
	}
}
