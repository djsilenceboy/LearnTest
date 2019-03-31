
package com.djs.learn.ctc.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class BasicTransactionThroughputInfoTest
{
	private static final Logger log = LogManager.getLogger(BasicTransactionInfo.class);

	@Test
	public void testProperty(){
		BasicTransactionThroughputInfo btti = new BasicTransactionThroughputInfo();
		assertEquals(-1, btti.getThroughput());
		btti.setThroughput(10);
		assertEquals(10, btti.getThroughput());
		btti.reset();
		assertEquals(-1, btti.getThroughput());
	}

	@Test
	public void testToString(){
		BasicTransactionThroughputInfo btti = new BasicTransactionThroughputInfo();
		log.debug("BasicTransactionThroughputInfo = " + btti.toSimpleString());
		log.debug("BasicTransactionThroughputInfo = " + btti);
		assertNotNull(btti.toSimpleString());
		assertNotNull(btti.toString());
	}
}
