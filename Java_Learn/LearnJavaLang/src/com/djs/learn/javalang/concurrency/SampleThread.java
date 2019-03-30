
package com.djs.learn.javalang.concurrency;

public class SampleThread extends Thread
{
	private int branch;
	private SampleResourceInterface sampleResource;
	private int loop;

	public SampleThread(SampleResourceInterface sampleResource, int branch, int loop){
		this.branch = branch;
		this.sampleResource = sampleResource;
		this.loop = loop;
	}

	@Override
	public void run(){
		String logId = "[" + Thread.currentThread().getName() + "]<" + branch + "> ";

		System.out.println(logId + "Enter... ");

		int i = 0;

		while (i++ < loop) {
			if (branch == 0) {
				System.out.println(logId + "= " + sampleResource.getCount0());
			} else if (branch == 1) {
				System.out.println(logId + "= " + sampleResource.getCount1());
			} else {
				System.out.println(logId + "= " + sampleResource.getCount2());
			}
		}

		System.out.println(logId + "Leave... ");
	}
}
