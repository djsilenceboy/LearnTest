
package dj.test.javalang.concurrency;

public class TestNotify
{
	static class SampleClient implements Runnable
	{
		private Object lock;
		private String keyword;

		public SampleClient(Object lock, String keyword){
			this.lock = lock;
			this.keyword = keyword;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			while (true) {
				synchronized (lock) {
					try {
						System.out.println(logId + keyword);
						lock.notify();
						lock.wait();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		Object lock = new Object();
		int count = 2;

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread(new SampleClient(lock, "Num-" + i));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
	}
}
