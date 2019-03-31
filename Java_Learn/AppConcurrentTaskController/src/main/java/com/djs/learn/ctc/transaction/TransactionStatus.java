
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
	INVALID, WAITING, PROCESSING, SUCCEEDED, FAILED, CLOSED
}
