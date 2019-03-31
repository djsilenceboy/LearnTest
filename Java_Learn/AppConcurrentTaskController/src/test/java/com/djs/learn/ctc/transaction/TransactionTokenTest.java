
package com.djs.learn.ctc.transaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionTokenTest
{
	@Test
	public void testDescription(){
		assertEquals("Available", TransactionToken.AVAILABLE.getDescription());
	}
}
