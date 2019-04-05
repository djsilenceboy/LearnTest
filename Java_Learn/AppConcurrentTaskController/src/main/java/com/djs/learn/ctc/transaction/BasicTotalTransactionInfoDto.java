
package com.djs.learn.ctc.transaction;

import java.io.Serializable;

/**
 * Basic total transaction info DTO.
 */
public class BasicTotalTransactionInfoDto implements Cloneable, Serializable
{
	private static final long serialVersionUID = 6068489168470017400L;

	/**
	 * Total transactions.
	 */
	protected long totalTransactions = 0;
	/**
	 * Total duration time of all transactions.
	 * <p>
	 * In millisecond.
	 */
	protected double totalTransactionDurationTime = 0.0;
	/**
	 * Average duration time of all transactions.
	 * <p>
	 * In millisecond.
	 */
	protected double averageTransactionDurationTime = 0.0;
	/**
	 * Minimum duration time.
	 * <p>
	 * In millisecond.<br>
	 * Initialized as a very big number.
	 */
	protected double minimumTransactionDurationTime = 3600000.0;
	/**
	 * Maximum duration time.
	 * <p>
	 * In millisecond.<br>
	 * Initialized as a very small number.
	 */
	protected double maximumTransactionDurationTime = 0.0;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	public long getTotalTransactions(){
		return totalTransactions;
	}

	public void setTotalTransactions(long totalTransactions){
		this.totalTransactions = totalTransactions;
	}

	public double getTotalTransactionDurationTime(){
		return totalTransactionDurationTime;
	}

	public void setTotalTransactionDurationTime(double totalTransactionDurationTime){
		this.totalTransactionDurationTime = totalTransactionDurationTime;
	}

	public double getAverageTransactionDurationTime(){
		return averageTransactionDurationTime;
	}

	public void setAverageTransactionDurationTime(double averageTransactionDurationTime){
		this.averageTransactionDurationTime = averageTransactionDurationTime;
	}

	public double getMinimumTransactionDurationTime(){
		return minimumTransactionDurationTime;
	}

	public void setMinimumTransactionDurationTime(double minimumTransactionDurationTime){
		this.minimumTransactionDurationTime = minimumTransactionDurationTime;
	}

	public double getMaximumTransactionDurationTime(){
		return maximumTransactionDurationTime;
	}

	public void setMaximumTransactionDurationTime(double maximumTransactionDurationTime){
		this.maximumTransactionDurationTime = maximumTransactionDurationTime;
	}
}
