
package com.djs.learn.javalang.hook;

public class TestMain
{
	public Thread hookThread = null;

	public void addHookThread(){
		System.out.println("Add hook thread...");

		hookThread = new Thread() {
			@Override
			public void run(){
				freeResource();
			}
		};

		Runtime.getRuntime().addShutdownHook(hookThread);
	}

	public void removeHookThread(){
		System.out.println("Remove hook thread...");

		if (hookThread != null) {
			Runtime.getRuntime().removeShutdownHook(hookThread);
			hookThread = null;
		}
	}

	public void freeResource(){
		System.out.println("Free something...");
	}

	public static void main(String[] args){
		TestMain tshHook = new TestMain();

		tshHook.addHookThread();

		try {
			int i;
			System.out.println("Sleeping...");
			for (i = 0; i < 50; i++) {
				Thread.sleep(100);
				System.out.println("i = " + i);
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		tshHook.removeHookThread();
	}
}
