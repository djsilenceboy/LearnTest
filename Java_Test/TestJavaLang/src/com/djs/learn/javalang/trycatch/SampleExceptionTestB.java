
package com.djs.learn.javalang.trycatch;

import java.net.ProtocolException;

public class SampleExceptionTestB extends SampleExceptionTestA
{
	@Override
	public void testThrows() throws ProtocolException, SecurityException{
		// ProtocolException is subclass of IOException;
		// SecurityException is subclass of RuntimeException.
	}
}
