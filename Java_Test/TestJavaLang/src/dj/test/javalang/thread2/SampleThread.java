
package dj.test.javalang.thread2;

public class SampleThread extends Thread
{
	@Override
	public void run(){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "enter... ");

		int i = 0;
		int max = 20;
		long sleepTime = 100;

		while (i++ < max) {
			System.out.println(logId + " = " + i);

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// These two flags are always return false!!!
				System.out.println(logId + " = " + i + " : " + this.isInterrupted() + " / " + Thread.interrupted());
			}
		}

		System.out.println(logId + " leave... ");
	}
}
