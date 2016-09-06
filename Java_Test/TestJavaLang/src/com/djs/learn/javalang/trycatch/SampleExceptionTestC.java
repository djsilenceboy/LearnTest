
package com.djs.learn.javalang.trycatch;

public class SampleExceptionTestC extends SampleExceptionTestA
{
	@Override
	public void testThrows() throws SecurityException{
		// SecurityException is subset of IOException, RuntimeException.
	}
}
