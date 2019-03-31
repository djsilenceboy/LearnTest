
package com.djs.learn.ctc.transaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionStatusTest
{
	@Test
	public void testDescription(){
		assertEquals("Waiting", TransactionStatus.WAITING.getDescription());
	}
}
