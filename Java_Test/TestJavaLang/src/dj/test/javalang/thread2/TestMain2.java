
package dj.test.javalang.thread2;

public class TestMain2
{
	public static void main(String[] args){
		SampleThread sampleThread1 = new SampleThread();
		SampleThread sampleThread2 = new SampleThread();

		sampleThread1.start();
		sampleThread2.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		sampleThread1.interrupt();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		sampleThread2.interrupt();
	}
}
