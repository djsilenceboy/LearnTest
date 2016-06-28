
package dj.test.javalang.concurrency;

public class TestNotifyAll
{
	static class SampleClient implements Runnable
	{
		private Object lock;

		public SampleClient(Object lock){
			this.lock = lock;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			while (true) {
				synchronized (lock) {
					try {
						System.out.println(logId + "Wait.");
						lock.wait();
					} catch (Exception e) {
					}

					try {

						System.out.println(logId + "Get lock.");
						Thread.sleep(200);
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		Object lock = new Object();
		int count = 4;

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread(new SampleClient(lock));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(50);
		} catch (Exception e) {
		}

		for (int i = 0; i < count; i++) {
			synchronized (lock) {
				System.out.println(logId + "Nofity all.");
				lock.notifyAll();
			}

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
