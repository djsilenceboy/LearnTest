
package dj.test.javalang.thread;

public class TestMain
{
	public void runThread(boolean isDaemon, long sleepTime){
		String logId = "[L0:" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "[runThread] Enter...");

		SampleThread th = new SampleThread(sleepTime);
		th.setDaemon(isDaemon);
		th.start();

		System.out.println(logId + "[runThread] Leave...");
	}

	public static void main(String[] args){
		String logId = "[L0:" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "Start.");

		try {
			TestMain tt = new TestMain();

			// Main process will be end before all threads.
			// So, you will observe "[L0:main] End." before all "[L1:xxx]" and "[L1:xxx] Hooked" (if any).

			// 15000 thread is Daemon thread. It will only be hooked after all other non-Daemon threads are over.
			// So, you will observe some "[L1:xxx] Leave with real passed".
			// And last, you will observe "[L1:xxx] Hooked" for Daemon thread.
			tt.runThread(true, 15000);
			Thread.sleep(200);
			tt.runThread(false, 6000);
			Thread.sleep(200);
			tt.runThread(false, 4000);
		} catch (Exception e) {
		}

		System.out.println(logId + "End.");
	}
}
