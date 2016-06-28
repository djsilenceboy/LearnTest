
package dj.test.javalang.concurrency;

// Use volatile to sync one variable.
// Succeeded.

public class SampleResourceC2 extends AbstractSampleResource
{
	volatile private long count = 0;

	@Override
	long getCount(){
		long value = count++;
		doSleep();
		return value;
	}

	@Override
	public long getCurrentCount(){
		return count;
	}

	public long getCount0(){
		return getCount();
	}

	public long getCount1(){
		return getCount();
	}

	public long getCount2(){
		return getCount();
	}
}
