
package com.djs.learn.javalang.concurrency;

// Use synchronized method and synchronized(this).
// synchronized method = synchronized(this)
// Succeeded.

public class SampleResourceA extends AbstractSampleResource
{
	synchronized public long getCount0(){
		return getCount();
	}

	synchronized public long getCount1(){
		return getCount();
	}

	public long getCount2(){
		synchronized (this) {
			return getCount();
		}
	}
}
