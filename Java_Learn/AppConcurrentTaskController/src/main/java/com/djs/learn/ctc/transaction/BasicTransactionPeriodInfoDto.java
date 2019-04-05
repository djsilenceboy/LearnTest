
package com.djs.learn.ctc.transaction;

import java.io.Serializable;

/**
 * Basic transaction period info DTO.
 */
public class BasicTransactionPeriodInfoDto implements Cloneable, Serializable
{
	private static final long serialVersionUID = 759985134971923080L;

	/**
	 * Internal ID.
	 * <p>
	 * For debugging/logging purpose.<br>
	 * May be unique or not.
	 */
	protected String internalId = "NoId";

	/**
	 * Transaction period start time.
	 * <p>
	 * In millisecond.
	 */
	protected long transactionPeriodStartTime = -1;
	/**
	 * Transaction period stop time.
	 * <p>
	 * In millisecond.
	 */
	protected long transactionPeriodStopTime = -1;
	/**
	 * Number of used transactions for current transaction period.
	 * <p>
	 * The used transaction will be released after the transaction is over.
	 */
	protected int usedTransactions = -1;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	public String getInternalId(){
		return internalId;
	}

	public void setInternalId(String internalId){
		this.internalId = internalId;
	}

	public long getTransactionPeriodStartTime(){
		return transactionPeriodStartTime;
	}

	public void setTransactionPeriodStartTime(long transactionPeriodStartTime){
		this.transactionPeriodStartTime = transactionPeriodStartTime;
	}

	public long getTransactionPeriodStopTime(){
		return transactionPeriodStopTime;
	}

	public void setTransactionPeriodStopTime(long transactionPeriodStopTime){
		this.transactionPeriodStopTime = transactionPeriodStopTime;
	}

	public int getUsedTransactions(){
		return usedTransactions;
	}

	public void setUsedTransactions(int usedTransactions){
		this.usedTransactions = usedTransactions;
	}
}
