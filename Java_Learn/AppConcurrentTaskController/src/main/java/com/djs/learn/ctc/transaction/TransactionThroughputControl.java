
package com.djs.learn.ctc.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Transaction throughput control.
 */
public class TransactionThroughputControl
{
	/**
	 * The logger.
	 */
	private static final Logger log = LogManager.getLogger(TransactionThroughputControl.class);

	/**
	 * Transaction throughput info.
	 */
	private BasicTransactionThroughputInfo btti = null;

	/**
	 * Constructor.
	 */
	public TransactionThroughputControl(){
		if (log.isTraceEnabled()) {
			log.trace("Enter...");
		}

		btti = new BasicTransactionThroughputInfo();
	}

	/**
	 * Constructor.
	 *
	 * @param btti
	 *        BasicTransactionThroughputInfo.
	 */
	public TransactionThroughputControl(BasicTransactionThroughputInfo btti){
		if (log.isTraceEnabled()) {
			log.trace("Enter...");
		}

		this.btti = btti;
	}

	/**
	 * Get transaction throughput info.
	 * <p>
	 * It is a cloned copy.
	 *
	 * @return BasicTransactionThroughputInfo - Info.
	 */
	public synchronized BasicTransactionThroughputInfo getTransactionThroughputInfo(){
		return (BasicTransactionThroughputInfo)btti.clone();
	}

	/**
	 * Set throughput.
	 *
	 * @param data
	 *        int, >= 1.
	 * @throws Exception
	 */
	public synchronized void setThroughput(int data) throws Exception{
		if (data > 0) {
			btti.setThroughput(data);

			if (log.isDebugEnabled()) {
				log.debug("Throughput = " + btti.getThroughput());
			}
		} else {
			throw new Exception("Throughout must >= 1.");
		}
	}

	/**
	 * Set transactions period time.
	 *
	 * @param time
	 *        long, in milliseconds, >= 1.
	 * @throws Exception
	 */
	public synchronized void setTransactionPeriodTime(long time) throws Exception{
		if (time > 0) {
			btti.setTransactionPeriodTime(time);

			if (log.isDebugEnabled()) {
				log.debug("Transaction period time = " + btti.getTransactionPeriodTime());
			}
		} else {
			throw new Exception("Transaction period time must >= 1.");
		}
	}

	/**
	 * Set max transactions per period.
	 *
	 * @param data
	 *        int, >= 1.
	 * @throws Exception
	 */
	public synchronized void setMaxTransactionsPerPeriod(int data) throws Exception{
		if (data > 0) {
			btti.setMaxTransactionsPerPeriod(data);

			if (log.isDebugEnabled()) {
				log.debug("Max transactions per period = " + btti.getMaxTransactionsPerPeriod());
			}
		} else {
			throw new Exception("Max transactions per period must >= 1.");
		}
	}

	/**
	 * Start control.
	 */
	public synchronized void startControl(){
		// Start from multiple of TransactionPeriodTime.
		btti.setStartTime((System.currentTimeMillis() / btti.getTransactionPeriodTime()) * btti.getTransactionPeriodTime());
		btti.setTotalTransactions(0);
		btti.setEffectivePeriods(0);
		btti.setCurrentThroughput(0);

		if (log.isInfoEnabled()) {
			log.info("Start time (ms) = " + btti.getStartTime());
			log.info("Total transactions = " + btti.getTotalTransactions());
			log.info("Effective periods = " + btti.getEffectivePeriods());
			log.info("Current throughput = " + btti.getCurrentThroughput());
		}

		// Total start time is also start time of first period.
		setCurrentPeriodStartTime(btti.getStartTime());
	}

	/**
	 * Stop control.
	 */
	public synchronized void stopControl(){
		btti.setStopTime(System.currentTimeMillis());
		btti.setTotalDurationTime(btti.getStopTime() - btti.getStartTime() + 1);

		if (log.isInfoEnabled()) {
			log.info("Stop time (ms) = " + btti.getStopTime());
			log.info("Total duration time (ms) = " + btti.getTotalDurationTime());
		}

		if ((btti.getTotalTransactions() > 0) || (btti.getCurrentTransactionsPerPeriod() > 0)) {
			// Count last current TPP, if any.
			if (btti.getCurrentTransactionsPerPeriod() > 0) {
				if (log.isTraceEnabled()) {
					log.trace("Period from " + btti.getCurrentPeriodStartTime() + " ms to " + btti.getCurrentPeriodStopTime() + " ms with transaction "
					        + btti.getCurrentTransactionsPerPeriod() + ".");
				}

				// If it is new min value.
				if (btti.getEffectiveMinTransactionsPerPeriod() > btti.getCurrentTransactionsPerPeriod()) {
					btti.setEffectiveMinTransactionsPerPeriod(btti.getCurrentTransactionsPerPeriod());
				}

				// If it is new max value.
				if (btti.getEffectiveMaxTransactionsPerPeriod() < btti.getCurrentTransactionsPerPeriod()) {
					btti.setEffectiveMaxTransactionsPerPeriod(btti.getCurrentTransactionsPerPeriod());
				}

				// Count for total.
				btti.setTotalTransactions(btti.getTotalTransactions() + btti.getCurrentTransactionsPerPeriod());
				btti.setEffectivePeriods(btti.getEffectivePeriods() + 1);
			}

			btti.setEffectiveAverageTransactionsPerPeriod(Math.round(((double)btti.getTotalTransactions() / btti.getEffectivePeriods()) * 100.0) / 100.0);
			btti.setAverageTransactionsPerPeriod(Math
			        .round((btti.getTotalTransactions() / ((double)btti.getTotalDurationTime() / btti.getTransactionPeriodTime())) * 100.0) / 100.0);
		}

		if (log.isInfoEnabled()) {
			log.info("Throughput                                = " + btti.getThroughput());
			log.info("Transaction period time (ms)              = " + btti.getTransactionPeriodTime());
			log.info("Max transactions per period               = " + btti.getMaxTransactionsPerPeriod());
			log.info("Total transactions                        = " + btti.getTotalTransactions());
			log.info("Effective periods                         = " + btti.getEffectivePeriods());
			log.info("Effective min transactions per period     = " + btti.getEffectiveMinTransactionsPerPeriod());
			log.info("Effective max transactions per period     = " + btti.getEffectiveMaxTransactionsPerPeriod());
			log.info("Effective average transactions per period = " + btti.getEffectiveAverageTransactionsPerPeriod());
			log.info("Average transactions per period           = " + btti.getAverageTransactionsPerPeriod());
		}
	}

	/**
	 * Set current period start time.
	 *
	 * @param time
	 *        long, in milliseconds. "-1" mean current time.
	 */
	private void setCurrentPeriodStartTime(long time){
		btti.setCurrentPeriodStartTime((time >= 0) ? time : System.currentTimeMillis());
		btti.setCurrentPeriodStopTime(btti.getCurrentPeriodStartTime() + btti.getTransactionPeriodTime() - 1);
		btti.setCurrentTransactionsPerPeriod(0);

		if (log.isTraceEnabled()) {
			log.trace("Current period start time (ms) = " + btti.getCurrentPeriodStartTime());
			log.trace("Current period stop time (ms)  = " + btti.getCurrentPeriodStopTime());
			log.trace("Current transactions per period (0) = " + btti.getCurrentTransactionsPerPeriod() + " / " + btti.getMaxTransactionsPerPeriod());
		}
	}

	/**
	 * Get current period remain time.
	 *
	 * @return long - Current period remain time in milliseconds.
	 */
	public synchronized long getCurrentPeriodRemainTime(){
		return btti.getCurrentPeriodStopTime() - System.currentTimeMillis();
	}

	/**
	 * Check if transaction is in current period.
	 *
	 * @param transactionStartTime
	 *        long, in milliseconds.
	 * @return boolean - true = yes.
	 */
	private boolean isTransactionInCurrentPeriod(long transactionStartTime){
		return (transactionStartTime >= btti.getCurrentPeriodStartTime()) && (transactionStartTime <= btti.getCurrentPeriodStopTime());
	}

	/**
	 * Get transaction token.
	 * <p>
	 * If get token, call returnTransactionToken() after processing transaction.<br>
	 * If token is not available due to reach max transactions per period, getCurrentPeriodRemainTime() and sleep.<br>
	 * If token is not available due to reach max throughput, sleep for a while and try this function again.
	 *
	 * @param bttiDst
	 *        BasicTransactionThroughputInfo, would be filled by this method.<br>
	 *        Check returned bttiDst.iCurrentTransactionsPerPeriod. If it is > 0, there is a completed period.<br>
	 *        Set to null, if not used.
	 * @return - TransactionToken, token status.
	 * @throws RuntimeException
	 *         Unexpected error.
	 */
	public synchronized TransactionToken getTransactionToken(BasicTransactionThroughputInfo bttiDst) throws RuntimeException{
		TransactionToken token = TransactionToken.INVALID;
		long checkTime = System.currentTimeMillis();

		if (checkTime < btti.getCurrentPeriodStartTime()) {
			throw new RuntimeException("Transaction start time is < current period start time. It is impossible.");
		}

		// If:
		// 1. new transaction would start after current/last period. That means current/last period had been over.
		// 2. current/last period is an effective one.
		// Then, count info and reset for new period.
		if (!isTransactionInCurrentPeriod(checkTime) && (btti.getCurrentTransactionsPerPeriod() > 0)) {
			if (log.isTraceEnabled()) {
				log.trace("Period from " + btti.getCurrentPeriodStartTime() + " ms to " + btti.getCurrentPeriodStopTime() + " ms with transaction "
				        + btti.getCurrentTransactionsPerPeriod() + ".");
			}

			// If it is new min value.
			if (btti.getEffectiveMinTransactionsPerPeriod() > btti.getCurrentTransactionsPerPeriod()) {
				btti.setEffectiveMinTransactionsPerPeriod(btti.getCurrentTransactionsPerPeriod());
			}

			// If it is new max value.
			if (btti.getEffectiveMaxTransactionsPerPeriod() < btti.getCurrentTransactionsPerPeriod()) {
				btti.setEffectiveMaxTransactionsPerPeriod(btti.getCurrentTransactionsPerPeriod());
			}

			// Count for total.
			btti.setTotalTransactions(btti.getTotalTransactions() + btti.getCurrentTransactionsPerPeriod());
			btti.setEffectivePeriods(btti.getEffectivePeriods() + 1);

			// Return completed period information.

			if (bttiDst != null) {
				btti.copy(bttiDst);
			}

			// Reset info.
			long passedTimeForNewPeriod = (checkTime - btti.getCurrentPeriodStopTime()) % btti.getTransactionPeriodTime();
			setCurrentPeriodStartTime(checkTime - passedTimeForNewPeriod + 1);
		}

		// Token is available when transactions per period does not exceed max value and there is free throughput slot.
		if (btti.getCurrentTransactionsPerPeriod() >= btti.getMaxTransactionsPerPeriod()) {
			token = TransactionToken.MAX_TRANSACTIONS;
		} else if (btti.getCurrentThroughput() >= btti.getThroughput()) {
			token = TransactionToken.MAX_THROUGHPUT;
		} else {
			token = TransactionToken.AVAILABLE;

			// Now, count this token.
			btti.setCurrentThroughput(btti.getCurrentThroughput() + 1);

			if (log.isTraceEnabled()) {
				log.trace("Current throughput (+) = " + btti.getCurrentThroughput() + " / " + btti.getThroughput());
			}
		}

		if (log.isTraceEnabled()) {
			StringBuilder temp = new StringBuilder();

			temp.append("Token = ");
			temp.append(token);
			temp.append(" <");
			temp.append(token.getDescription());
			temp.append(">");

			log.trace(temp);
		}

		return token;
	}

	/**
	 * Get transaction token.
	 * <p>
	 * If get token, call returnTransactionToken() after processing transaction.<br>
	 * If token is not available due to reach max transactions per period, getCurrentPeriodRemainTime() and sleep.<br>
	 * If token is not available due to reach max throughput, sleep for a while and try this function again.
	 *
	 * @return - TransactionToken, token status.
	 * @throws RuntimeException
	 *         Unexpected error.
	 */
	public TransactionToken getTransactionToken() throws RuntimeException{
		return getTransactionToken(null);
	}

	/**
	 * Count transaction.
	 * <p>
	 * This function should be called:<br>
	 * <ol>
	 * <li>after getting token and before release token.
	 * <li>immediately after getting token.
	 * <li>only once for one transaction.
	 * </ol>
	 */
	public synchronized void countTransaction(){
		btti.setCurrentTransactionsPerPeriod(btti.getCurrentTransactionsPerPeriod() + 1);

		if (log.isTraceEnabled()) {
			log.trace("Current transactions per period (+) = " + btti.getCurrentTransactionsPerPeriod() + " / " + btti.getMaxTransactionsPerPeriod());
		}
	}

	/**
	 * Release transaction token.
	 * <p>
	 * Call this function to release token got by getTransactionToken().
	 *
	 * @throws RuntimeException
	 *         Unexpected error.
	 */
	public synchronized void releaseTransactionToken() throws RuntimeException{
		// Only discount this token for throughput.
		// Because throughput is limited resource, while TransactionsPerPeriod is loop counting.
		btti.setCurrentThroughput(btti.getCurrentThroughput() - 1);

		if (log.isTraceEnabled()) {
			log.trace("Current throughput (-) = " + btti.getCurrentThroughput() + " / " + btti.getThroughput());
		}

		if (btti.getCurrentThroughput() < 0) {
			throw new RuntimeException("Current throughput < 0. It is impossible.");
		}
	}

	/**
	 * Check if all transaction tokens are released.
	 *
	 * @return boolean - true = yes.
	 */
	public synchronized boolean isAllTransactionTokensReleased(){
		return (btti.getCurrentThroughput() <= 0);
	}
}
