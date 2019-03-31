
package com.djs.learn.ctc.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Basic transaction info.
 */
public class BasicTransactionInfo
{
	/**
	 * The logger.
	 */
	private static final Logger log = LogManager.getLogger(BasicTransactionInfo.class);

	/**
	 * Transaction ID.
	 * <p>
	 * For debugging purpose.
	 */
	private long transactionId;
	/**
	 * Transaction start time in millisecond.
	 */
	private long transactionStartTime;
	/**
	 * Transaction stop time in millisecond.
	 */
	private long transactionStopTime;
	/**
	 * Transaction duration time in millisecond.
	 */
	private long transactionDurationTime;
	/**
	 * Transaction status.
	 */
	private TransactionStatus transactionStatus;

	/**
	 * Constructor.
	 */
	public BasicTransactionInfo(){
		reset();
	}

	/**
	 * Reset info.
	 */
	public void reset(){
		transactionId = -1;
		transactionStartTime = -1;
		transactionStopTime = -1;
		transactionDurationTime = -1;
		transactionStatus = TransactionStatus.INVALID;
	}

	/**
	 * Get transaction status text.
	 *
	 * @return String
	 */
	public static String getTransactionStatusText(TransactionStatus status){
		String temp = null;

		if (status == TransactionStatus.WAITING) {
			temp = "Waiting";
		} else if (status == TransactionStatus.PROCESSING) {
			temp = "Processing";
		} else if (status == TransactionStatus.SUCCEEDED) {
			temp = "Succeeded";
		} else if (status == TransactionStatus.FAILED) {
			temp = "Failed";
		} else if (status == TransactionStatus.CLOSED) {
			temp = "Closed";
		} else {
			temp = "Invalid";
		}

		return temp;
	}

	/**
	 * Set transaction ID.
	 *
	 * @param id
	 *        long.
	 */
	public void setTransactionId(long id){
		transactionId = id;
	}

	/**
	 * Get transaction ID.
	 *
	 * @return long - Transaction ID.
	 */
	public long getTransactionId(){
		return transactionId;
	}

	/**
	 * Set transaction start time.
	 *
	 * @param time
	 *        long, in milliseconds, "-1" means current time.
	 */
	public synchronized void setTransactionStartTime(long time){
		transactionStartTime = (time >= 0) ? time : System.currentTimeMillis();

		if (log.isTraceEnabled()) {
			log.trace("Transaction ID " + transactionId + ": Transaction start time (ms) = " + transactionStartTime);
		}
	}

	/**
	 * Get transaction start time.
	 *
	 * @return long - Transaction start time in milliseconds.
	 */
	public long getTransactionStartTime(){
		return transactionStartTime;
	}

	/**
	 * Set transaction stop time.
	 *
	 * @param time
	 *        long, in milliseconds, "-1" means current time.
	 */
	public synchronized void setTransactionStopTime(long time) throws Exception{
		transactionStopTime = (time >= 0) ? time : System.currentTimeMillis();
		transactionDurationTime = transactionStopTime - getTransactionStartTime() + 1;

		if (log.isTraceEnabled()) {
			log.trace("Transaction ID " + transactionId + ": Transaction stop time (ms) = " + transactionStopTime);
			log.trace("Transaction ID " + transactionId + ": Transaction duration time (ms) = " + transactionDurationTime);
		}

		if (transactionDurationTime < 0) {
			throw new Exception("Transaction end time must >= transaction start time.");
		}
	}

	/**
	 * Get transaction stop time.
	 *
	 * @return long - Transaction stop time in milliseconds.
	 */
	public long getTransactionStopTime(){
		return transactionStopTime;
	}

	/**
	 * Get transaction duration time.
	 *
	 * @return long - Transaction duration time in milliseconds.
	 */
	public long getTransactionDurationTime(){
		return transactionDurationTime;
	}

	/**
	 * Set transaction status.
	 *
	 * @param status
	 *        TransactionStatus, transaction status.
	 */
	public synchronized void setTransactionStatus(TransactionStatus status){
		transactionStatus = status;

		if (log.isTraceEnabled()) {
			log.trace("Transaction ID " + transactionId + ": Transaction status = " + status + "<" + getTransactionStatusText(status) + ">");
		}
	}

	/**
	 * Get transaction status.
	 *
	 * @return TransactionStatus - Transaction status.
	 */
	public synchronized TransactionStatus getTransactionStatus(){
		return transactionStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder line = new StringBuilder();

		line.append("Transaction ID:");
		line.append(transactionId);
		line.append(", Transaction start time:");
		line.append(transactionStartTime);
		line.append(", Transaction stop time:");
		line.append(transactionStopTime);
		line.append(", Transaction duration time:");
		line.append(transactionDurationTime);
		line.append(", Transaction status:");
		line.append(transactionStatus);
		line.append("<");
		line.append(getTransactionStatusText(transactionStatus));
		line.append(">");

		return line.toString();
	}

	/**
	 * To simple string.
	 *
	 * @return String
	 */
	public String toSimpleString(){
		StringBuilder line = new StringBuilder();

		line.append(transactionId);
		line.append(",");
		line.append(transactionStartTime);
		line.append(",");
		line.append(transactionStopTime);
		line.append(",");
		line.append(transactionDurationTime);
		line.append(",");
		line.append(transactionStatus);
		line.append("<");
		line.append(getTransactionStatusText(transactionStatus));
		line.append(">");

		return line.toString();
	}
}
