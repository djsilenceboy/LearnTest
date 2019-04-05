
package com.djs.learn.ctc.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Duration;

import org.junit.Test;

import com.djs.learn.ctc.thread.BasicSleepHelper;

public class TransactionThroughputControlTest
{
	@Test
	public void testConstructorWithDefault(){
		TransactionThroughputControl ttc = new TransactionThroughputControl();
		assertNotNull(ttc.getTransactionThroughputInfo());
	}

	@Test
	public void testConstructorWithInput(){
		BasicTransactionThroughputInfo btti = new BasicTransactionThroughputInfo();
		btti.setCurrentThroughput(123);
		TransactionThroughputControl ttc = new TransactionThroughputControl(btti);
		assertEquals(btti.getCurrentThroughput(), ttc.getTransactionThroughputInfo().getCurrentThroughput());
	}

	@Test(expected = Exception.class)
	public void testSetThroughputError() throws Exception{
		TransactionThroughputControl ttc = new TransactionThroughputControl();
		ttc.setThroughput(0);
	}

	@Test(expected = Exception.class)
	public void testSetTransactionPeriodTimeError() throws Exception{
		TransactionThroughputControl ttc = new TransactionThroughputControl();
		ttc.setTransactionPeriodTime(Duration.ofMillis(0));
	}

	@Test(expected = Exception.class)
	public void testSetMaxTransactionsPerPeriodError() throws Exception{
		TransactionThroughputControl ttc = new TransactionThroughputControl();
		ttc.setMaxTransactionsPerPeriod(0);
	}

	@Test
	public void testStartStopControl() throws Exception{
		TransactionThroughputControl ttc = new TransactionThroughputControl();
		ttc.setTransactionPeriodTime(Duration.ofMillis(1000));
		ttc.startControl();
		BasicSleepHelper.normalSleep(1000);
		ttc.stopControl();
	}
}
