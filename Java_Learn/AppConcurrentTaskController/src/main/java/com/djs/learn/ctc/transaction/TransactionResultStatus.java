
package com.djs.learn.ctc.transaction;

/**
 * Transaction result status.
 * <p>
 * <ul>
 * <li>INVALID: Initial status for internal usage.</li>
 * <li>SUCCEEDED: Process succeeded.</li>
 * <li>FAILED: Process failed.</li>
 * <li>BROKEN: Process is broken in middle way. That means not succeeded or failed.</li>
 * </ul>
 */
public enum TransactionResultStatus
{
	INVALID("Invalid"), SUCCEEDED("Succeeded"), FAILED("Failed"), BROKEN("Broken");

	private final String description;

	private TransactionResultStatus(String description){
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
