
package com.djs.learn.ctc.transaction;

import java.io.Serializable;

/**
 * Basic transaction info DTO.
 */
public class BasicTransactionInfoDto implements Cloneable, Serializable
{
	private static final long serialVersionUID = 6599915981164141840L;

	/**
	 * Internal ID.
	 * <p>
	 * For debugging/logging purpose.<br>
	 * May be unique or not.<br>
	 * Normally, the ID is same as the one for BasicTransactionControlInfoDto.
	 */
	protected String internalId = "NoId";

	/**
	 * Token status.
	 */
	protected TransactionToken tokenStatus = null;
	/**
	 * Token ID.
	 * <p>
	 * An unique ID.<br>
	 * null means not assigned value.
	 */
	protected String tokenId = null;
	/**
	 * Token issue time.
	 * <p>
	 * In millisecond.
	 */
	protected long tokenIssueTime = -1;
	/**
	 * Token release/revoke time.
	 * <p>
	 * In millisecond.
	 */
	protected long tokenReleaseTime = -1;

	/**
	 * Transaction status.
	 */
	protected TransactionStatus transactionStatus = null;
	/**
	 * Transaction start time.
	 * <p>
	 * In millisecond.<br>
	 * May be same as tokenIssueTime.
	 */
	protected long transactionStartTime = -1;
	/**
	 * Transaction stop time.
	 * <p>
	 * In millisecond.<br>
	 * May be same as tokenReleaseTime.
	 */
	protected long transactionStopTime = -1;
	/**
	 * Transaction duration time.
	 * <p>
	 * In millisecond.
	 */
	protected long transactionDurationTime = -1;
	/**
	 * Transaction result status.
	 */
	protected TransactionResultStatus transactionResultStatus = null;

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

	public TransactionToken getTokenStatus(){
		return tokenStatus;
	}

	public void setTokenStatus(TransactionToken tokenStatus){
		this.tokenStatus = tokenStatus;
	}

	public String getTokenId(){
		return tokenId;
	}

	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}

	public long getTokenIssueTime(){
		return tokenIssueTime;
	}

	public void setTokenIssueTime(long tokenIssueTime){
		this.tokenIssueTime = tokenIssueTime;
	}

	public long getTokenReleaseTime(){
		return tokenReleaseTime;
	}

	public void setTokenReleaseTime(long tokenReleaseTime){
		this.tokenReleaseTime = tokenReleaseTime;
	}

	public TransactionStatus getTransactionStatus(){
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus){
		this.transactionStatus = transactionStatus;
	}

	public long getTransactionStartTime(){
		return transactionStartTime;
	}

	public void setTransactionStartTime(long transactionStartTime){
		this.transactionStartTime = transactionStartTime;
	}

	public long getTransactionStopTime(){
		return transactionStopTime;
	}

	public void setTransactionStopTime(long transactionStopTime){
		this.transactionStopTime = transactionStopTime;
	}

	public long getTransactionDurationTime(){
		return transactionDurationTime;
	}

	public void setTransactionDurationTime(long transactionDurationTime){
		this.transactionDurationTime = transactionDurationTime;
	}

	public TransactionResultStatus getTransactionResultStatus(){
		return transactionResultStatus;
	}

	public void setTransactionResultStatus(TransactionResultStatus transactionResultStatus){
		this.transactionResultStatus = transactionResultStatus;
	}
}
