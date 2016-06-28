
package dj.test.javalang.concurrency;

// No use if not synchronized all methods.
// Failed.

public class SampleResourceB extends AbstractSampleResource
{
	synchronized public long getCount0(){
		return getCount();
	}

	public long getCount1(){
		return getCount();
	}

	public long getCount2(){
		return getCount();
	}
}
