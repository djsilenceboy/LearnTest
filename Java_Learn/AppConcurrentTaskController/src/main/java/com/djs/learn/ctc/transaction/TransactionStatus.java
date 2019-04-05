
package com.djs.learn.ctc.transaction;

/**
 * Transaction status.
 * <p>
 * <ul>
 * <li>INVALID: Initial status for internal usage.</li>
 * <li>START: Start.</li>
 * <li>STOP: Stop.</li>
 * </ul>
 */
public enum TransactionStatus
{
	INVALID("Invalid"), START("Start"), STOP("Stop");

	private final String description;

	private TransactionStatus(String description){
		this.description = description;
	}

	/**
	 * Get description.
	 *
	 * @return String
	 */
	public String getDescription(){
		return description;
	}
}
