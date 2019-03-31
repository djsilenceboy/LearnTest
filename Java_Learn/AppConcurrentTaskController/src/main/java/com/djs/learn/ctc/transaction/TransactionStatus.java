
package com.djs.learn.ctc.transaction;

/**
 * Transaction status.
 * <p>
 * <ul>
 * <li>INVALID: Initial status for internal usage.</li>
 * <li>WAITING: Waiting for process.</li>
 * <li>PROCESSING: Processing.</li>
 * <li>SUCCEEDED: Process succeeded.</li>
 * <li>FAILED: Process failed.</li>
 * <li>CLOSED: Closed.</li>
 * </ul>
 */
public enum TransactionStatus
{
	INVALID("Invalid"), WAITING("Waiting"), PROCESSING("Processing"), SUCCEEDED("Succeeded"), FAILED("Failed"), CLOSED("Closed");

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
