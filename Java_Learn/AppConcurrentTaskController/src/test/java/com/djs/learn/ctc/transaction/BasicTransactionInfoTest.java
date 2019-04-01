
package com.djs.learn.ctc.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class BasicTransactionInfoTest
{
	private static final Logger log = LogManager.getLogger(BasicTransactionInfoTest.class);

	@Test
	public void testProperty(){
		BasicTransactionInfo bti = new BasicTransactionInfo();
		assertEquals(-1, bti.getTransactionId());
		bti.setTransactionId(10);
		assertEquals(10, bti.getTransactionId());
		bti.reset();
		assertEquals(-1, bti.getTransactionId());
	}

	@Test
	public void testToString(){
		BasicTransactionInfo bti = new BasicTransactionInfo();
		log.debug("BasicTransactionInfo = " + bti.toSimpleString());
		log.debug("BasicTransactionInfo = " + bti);
		assertNotNull(bti.toSimpleString());
		assertNotNull(bti.toString());
	}

	@Test
	public void testStartStopTime(){
		BasicTransactionInfo bti = new BasicTransactionInfo();
		bti.setTransactionId(10);

		assertNull(bti.getTransactionStartTime());
		bti.setTransactionStartTime(null);
		log.debug("Start time = " + bti.getTransactionStartTime());
		assertNotNull(bti.getTransactionStartTime());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Failed: " + e);
		}

		assertNull(bti.getTransactionStopTime());
		try {
			bti.setTransactionStopTime(null);
		} catch (Exception e) {
			log.error("Failed: " + e);
		}
		log.debug("Stop time = " + bti.getTransactionStopTime());
		assertNotNull(bti.getTransactionStopTime());

		assertTrue(bti.getTransactionDuration().toMillis() >= 1000);
	}

	@Test(expected = Exception.class)
	public void testNoStartTimeError() throws Exception{
		BasicTransactionInfo bti = new BasicTransactionInfo();
		bti.setTransactionId(10);

		bti.setTransactionStopTime(null);
	}

	@Test(expected = Exception.class)
	public void testStopTimeBeforeStartTimeError() throws Exception{
		BasicTransactionInfo bti = new BasicTransactionInfo();
		bti.setTransactionId(10);

		bti.setTransactionStartTime(null);
		bti.setTransactionStopTime(LocalDateTime.now().minusSeconds(5));
	}

	@Test
	public void testStatus(){
		BasicTransactionInfo bti = new BasicTransactionInfo();
		assertEquals(TransactionStatus.INVALID, bti.getTransactionStatus());
		bti.setTransactionStatus(TransactionStatus.PROCESSING);
		assertEquals(TransactionStatus.PROCESSING, bti.getTransactionStatus());
	}
}
