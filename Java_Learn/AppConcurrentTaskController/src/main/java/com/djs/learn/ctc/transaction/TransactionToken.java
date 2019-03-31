
package com.djs.learn.ctc.transaction;

/**
 * Transaction token.
 * <p>
 * <ul>
 * <li>INVALID: Initial status for internal usage.</li>
 * <li>AVAILABLE: Token available for transaction.</li>
 * <li>MAX_TRANSACTIONS: Token not available due to reach max transactions per period.</li>
 * <li>MAX_THROUGHPUT: Token not available due to reach max throughput.</li>
 * </ul>
 */
public enum TransactionToken
{
	INVALID("Invalid"), AVAILABLE("Available"), MAX_TRANSACTIONS("Max transactions"), MAX_THROUGHPUT("Max throughput");

	private final String description;

	private TransactionToken(String description){
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
