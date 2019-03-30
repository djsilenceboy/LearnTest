
package com.djs.learn.javalang.concurrency;

// Use synchronized static method and synchronized(Class).
// synchronized static method = synchronized(Class)

public class SampleResourceE
{
	private static SampleResourceA sampleResource = new SampleResourceA();

	synchronized public static long getCount0(){
		return sampleResource.getCount();
	}

	synchronized public static long getCount1(){
		return sampleResource.getCount();
	}

	public static long getCount2(){
		synchronized (SampleResourceE.class) {
			return sampleResource.getCount();
		}
	}
}
