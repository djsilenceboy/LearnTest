
package com.djs.learn.javalang.thread;

public class SampleThread extends Thread
{
	private long sleepTime;
	private String logId;

	public SampleThread(long sleepTime){
		this.sleepTime = sleepTime;
	}

	@Override
	public void run(){
		logId = "[L1:" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "Sleep time = " + sleepTime);

		System.out.println(logId + "Name = " + this.getName());
		System.out.println(logId + "ID = " + this.getId());
		System.out.println(logId + "Priority = " + this.getPriority());
		System.out.println(logId + "Daemon = " + this.isDaemon());

		new SampleSubThread().start();

		System.out.println(logId + "Thread count = " + Thread.activeCount());

		long lastTime;
		long currentTime;
		long passedTime;
		long remainedTime;
		long realPassedTime;

		lastTime = System.currentTimeMillis();
		passedTime = 0;
		remainedTime = sleepTime;
		realPassedTime = System.currentTimeMillis();

		addHookThread();

		do {
			System.out.println(logId + "Passed " + passedTime + " ms, remained " + remainedTime + " ms");

			try {
				sleep(remainedTime);

				passedTime = remainedTime;
				// No more remained. Set to quit do loop.
				remainedTime = 0;
			} catch (Exception e) {
				// Interrupted.

				// How must time is passed before interrupted.
				currentTime = System.currentTimeMillis();
				passedTime = currentTime - lastTime;
				remainedTime -= passedTime;
				lastTime = currentTime;
			}
		} while (remainedTime > 0);

		removeHookThread();

		realPassedTime = System.currentTimeMillis() - realPassedTime;

		System.out.println(logId + "Leave with real passed " + realPassedTime + " ms ... ");
	}

	// Hook.

	public Thread hookThread = null;

	public void addHookThread(){
		System.out.println(logId + "Add hook thread...");

		hookThread = new Thread() {
			@Override
			public void run(){
				hooked();
			}
		};

		Runtime.getRuntime().addShutdownHook(hookThread);
	}

	public void removeHookThread(){
		System.out.println(logId + "Remove hook thread...");

		if (hookThread != null) {
			Runtime.getRuntime().removeShutdownHook(hookThread);
			hookThread = null;
		}
	}

	public void hooked(){
		System.out.println(logId + "Hooked...");
	}
}
