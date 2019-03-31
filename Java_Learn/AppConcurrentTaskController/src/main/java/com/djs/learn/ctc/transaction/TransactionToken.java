
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
	INVALID, AVAILABLE, MAX_TRANSACTIONS, MAX_THROUGHPUT
}
