
package com.djs.learn.javalang.concurrency;

abstract class AbstractSampleResource implements SampleResourceInterface
{
	long count = 0;

	void doSleep(){
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
	}

	long getCount(){
		long value = count;
		doSleep();
		count++;
		return value;
	}

	@Override
	public long getCurrentCount(){
		return count;
	}
}
