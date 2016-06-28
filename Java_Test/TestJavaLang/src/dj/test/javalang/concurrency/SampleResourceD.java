
package dj.test.javalang.concurrency;

import java.util.concurrent.atomic.AtomicLong;

// Use Atomic to sync one variable.
// Succeeded.

public class SampleResourceD extends AbstractSampleResource
{
	private AtomicLong count = new AtomicLong(-1);

	@Override
	long getCount(){
		long value = count.incrementAndGet();
		doSleep();
		return value;
	}

	@Override
	public long getCurrentCount(){
		return count.get();
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
