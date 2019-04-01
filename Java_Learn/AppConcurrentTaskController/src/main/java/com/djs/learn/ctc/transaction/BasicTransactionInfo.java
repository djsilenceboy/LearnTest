
package com.djs.learn.ctc.transaction;

import java.time.Duration;
import java.time.LocalDateTime;

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
	 * Transaction start time.
	 */
	private LocalDateTime transactionStartTime;
	/**
	 * Transaction stop time.
	 */
	private LocalDateTime transactionStopTime;
	/**
	 * Transaction duration.
	 */
	private Duration transactionDuration;
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
		transactionStartTime = null;
		transactionStopTime = null;
		transactionDuration = null;
		transactionStatus = TransactionStatus.INVALID;
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
	 *        LocalDateTime, null means current time.
	 */
	public synchronized void setTransactionStartTime(LocalDateTime time){
		transactionStartTime = (time != null) ? time : LocalDateTime.now();

		if (log.isTraceEnabled()) {
			log.trace("Transaction ID " + transactionId + ": Transaction start time = " + transactionStartTime);
		}
	}

	/**
	 * Get transaction start time.
	 *
	 * @return LocalDateTime - Transaction start time.
	 */
	public LocalDateTime getTransactionStartTime(){
		return transactionStartTime;
	}

	/**
	 * Set transaction stop time.
	 *
	 * @param time
	 *        LocalDateTime, null means current time.
	 */
	public synchronized void setTransactionStopTime(LocalDateTime time) throws Exception{
		if (transactionStartTime == null) {
			throw new Exception("Transaction must have start time first.");
		}

		transactionStopTime = (time != null) ? time : LocalDateTime.now();
		transactionDuration = Duration.between(transactionStartTime, transactionStopTime);

		if (log.isTraceEnabled()) {
			log.trace("Transaction ID " + transactionId + ": Transaction stop time = " + transactionStopTime);
			log.trace("Transaction ID " + transactionId + ": Transaction duration (ms) = " + transactionDuration.toMillis());
		}

		if (transactionDuration.isNegative()) {
			throw new Exception("Transaction stop time must >= transaction start time.");
		}
	}

	/**
	 * Get transaction stop time.
	 *
	 * @return LocalDateTime - Transaction stop time.
	 */
	public LocalDateTime getTransactionStopTime(){
		return transactionStopTime;
	}

	/**
	 * Get transaction duration.
	 *
	 * @return Duration - Transaction duration.
	 */
	public Duration getTransactionDuration(){
		return transactionDuration;
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
			log.trace("Transaction ID " + transactionId + ": Transaction status = " + status + "<" + status.getDescription() + ">");
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
		line.append(", Transaction duration:");
		line.append((transactionDuration == null) ? -1 : transactionDuration.toMillis());
		line.append(", Transaction status:");
		line.append(transactionStatus);
		line.append("<");
		line.append(transactionStatus.getDescription());
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
		line.append((transactionDuration == null) ? -1 : transactionDuration.toMillis());
		line.append(",");
		line.append(transactionStatus);
		line.append("<");
		line.append(transactionStatus.getDescription());
		line.append(">");

		return line.toString();
	}
}
