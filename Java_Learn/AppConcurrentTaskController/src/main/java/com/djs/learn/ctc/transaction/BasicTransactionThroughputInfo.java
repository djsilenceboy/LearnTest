
package com.djs.learn.ctc.transaction;

/**
 * Basic transaction throughput info.
 */
public class BasicTransactionThroughputInfo implements Cloneable
{
	/**
	 * Throughput.
	 */
	private int throughput;
	/**
	 * Transaction period time in millisecond.
	 */
	private long transactionPeriodTime;
	/**
	 * Max transactions per period.
	 */
	private int maxTransactionsPerPeriod;

	/**
	 * Start time in millisecond.
	 */
	private long startTime;
	/**
	 * Stop time in millisecond.
	 */
	private long stopTime;
	/**
	 * Total duration in millisecond.
	 */
	private long totalDurationTime;
	/**
	 * Total transactions for all periods.
	 */
	private long totalTransactions;
	/**
	 * Effective periods count, periods with at least one transaction.
	 */
	private long effectivePeriods;
	/**
	 * Effective min transactions per period, (initial as 10000).
	 */
	private int effectiveMinTransactionsPerPeriod;
	/**
	 * Effective max transactions per period, (initial as 0).
	 */
	private int effectiveMaxTransactionsPerPeriod;
	/**
	 * Effective average transactions per period.
	 */
	private double effectiveAverageTransactionsPerPeriod;
	/**
	 * Average transactions per period.
	 */
	private double averageTransactionsPerPeriod;

	/**
	 * Current period start time.
	 */
	private long currentPeriodStartTime;
	/**
	 * Current period stop time.
	 */
	private long currentPeriodStopTime;

	/**
	 * Current throughput.
	 */
	private int currentThroughput;
	/**
	 * Current transactions per period.
	 */
	private int currentTransactionsPerPeriod;

	/**
	 * Constructor.
	 */
	public BasicTransactionThroughputInfo(){
		reset();
	}

	/**
	 * Reset info.
	 */
	public void reset(){
		throughput = -1;
		transactionPeriodTime = -1;
		maxTransactionsPerPeriod = -1;
		startTime = -1;
		stopTime = -1;
		totalDurationTime = -1;
		totalTransactions = -1;
		effectivePeriods = -1;
		effectiveMinTransactionsPerPeriod = 10000;
		effectiveMaxTransactionsPerPeriod = 0;
		effectiveAverageTransactionsPerPeriod = 0.0;
		averageTransactionsPerPeriod = 0.0;
		currentPeriodStartTime = -1;
		currentPeriodStopTime = -1;
		currentThroughput = -1;
		currentTransactionsPerPeriod = -1;
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

	/**
	 * Copy local object.
	 *
	 * @param destination
	 *        BasicTransactionThroughputInfo, destination.
	 */
	public void copy(BasicTransactionThroughputInfo destination){
		destination.throughput = throughput;
		destination.transactionPeriodTime = transactionPeriodTime;
		destination.maxTransactionsPerPeriod = maxTransactionsPerPeriod;
		destination.startTime = startTime;
		destination.stopTime = stopTime;
		destination.totalDurationTime = totalDurationTime;
		destination.totalTransactions = totalTransactions;
		destination.effectivePeriods = effectivePeriods;
		destination.effectiveMinTransactionsPerPeriod = effectiveMinTransactionsPerPeriod;
		destination.effectiveMaxTransactionsPerPeriod = effectiveMaxTransactionsPerPeriod;
		destination.effectiveAverageTransactionsPerPeriod = effectiveAverageTransactionsPerPeriod;
		destination.averageTransactionsPerPeriod = averageTransactionsPerPeriod;
		destination.currentPeriodStartTime = currentPeriodStartTime;
		destination.currentPeriodStopTime = currentPeriodStopTime;
		destination.currentThroughput = currentThroughput;
		destination.currentTransactionsPerPeriod = currentTransactionsPerPeriod;
	}

	/**
	 * Get transaction token text.
	 *
	 * @return String
	 */
	public static String getTransactionTokenText(TransactionToken token){
		String temp = null;

		if (token == TransactionToken.AVAILABLE) {
			temp = "Available";
		} else if (token == TransactionToken.MAX_TRANSACTIONS) {
			temp = "Max transactions";
		} else if (token == TransactionToken.MAX_THROUGHPUT) {
			temp = "Max throughput";
		} else {
			temp = "Invalid";
		}

		return temp;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder line = new StringBuilder();

		line.append("Throughput:");
		line.append(throughput);
		line.append(", Transaction period time:");
		line.append(transactionPeriodTime);
		line.append(", Max transactions per period:");
		line.append(maxTransactionsPerPeriod);
		line.append(", Start time:");
		line.append(startTime);
		line.append(", Stop time:");
		line.append(stopTime);
		line.append(", Total duration time:");
		line.append(totalDurationTime);
		line.append(", Total transactions:");
		line.append(totalTransactions);
		line.append(", Effective periods:");
		line.append(effectivePeriods);
		line.append(", Effective min transactions per period:");
		line.append(effectiveMinTransactionsPerPeriod);
		line.append(", Effective max transactions per period:");
		line.append(effectiveMaxTransactionsPerPeriod);
		line.append(", Effective average transactions per period:");
		line.append(effectiveAverageTransactionsPerPeriod);
		line.append(", Average transactions per period:");
		line.append(averageTransactionsPerPeriod);
		line.append(", Current period start time:");
		line.append(currentPeriodStartTime);
		line.append(", Current period stop time:");
		line.append(currentPeriodStopTime);
		line.append(", Current throughput:");
		line.append(currentThroughput);
		line.append(", Current transactions per period:");
		line.append(currentTransactionsPerPeriod);

		return line.toString();
	}

	/**
	 * To simple string.
	 *
	 * @return String
	 */
	public String toSimpleString(){
		StringBuilder line = new StringBuilder();

		line.append(throughput);
		line.append(",");
		line.append(transactionPeriodTime);
		line.append(",");
		line.append(maxTransactionsPerPeriod);
		line.append(",");
		line.append(startTime);
		line.append(",");
		line.append(stopTime);
		line.append(",");
		line.append(totalDurationTime);
		line.append(",");
		line.append(totalTransactions);
		line.append(",");
		line.append(effectivePeriods);
		line.append(",");
		line.append(effectiveMinTransactionsPerPeriod);
		line.append(",");
		line.append(effectiveMaxTransactionsPerPeriod);
		line.append(",");
		line.append(effectiveAverageTransactionsPerPeriod);
		line.append(",");
		line.append(averageTransactionsPerPeriod);
		line.append(",");
		line.append(currentPeriodStartTime);
		line.append(",");
		line.append(currentPeriodStopTime);
		line.append(",");
		line.append(currentThroughput);
		line.append(",");
		line.append(currentTransactionsPerPeriod);

		return line.toString();
	}

	public int getThroughput(){
		return throughput;
	}

	public void setThroughput(int throughput){
		this.throughput = throughput;
	}

	public long getTransactionPeriodTime(){
		return transactionPeriodTime;
	}

	public void setTransactionPeriodTime(long transactionPeriodTime){
		this.transactionPeriodTime = transactionPeriodTime;
	}

	public int getMaxTransactionsPerPeriod(){
		return maxTransactionsPerPeriod;
	}

	public void setMaxTransactionsPerPeriod(int maxTransactionsPerPeriod){
		this.maxTransactionsPerPeriod = maxTransactionsPerPeriod;
	}

	public long getStartTime(){
		return startTime;
	}

	public void setStartTime(long startTime){
		this.startTime = startTime;
	}

	public long getStopTime(){
		return stopTime;
	}

	public void setStopTime(long stopTime){
		this.stopTime = stopTime;
	}

	public long getTotalDurationTime(){
		return totalDurationTime;
	}

	public void setTotalDurationTime(long totalDurationTime){
		this.totalDurationTime = totalDurationTime;
	}

	public long getTotalTransactions(){
		return totalTransactions;
	}

	public void setTotalTransactions(long totalTransactions){
		this.totalTransactions = totalTransactions;
	}

	public long getEffectivePeriods(){
		return effectivePeriods;
	}

	public void setEffectivePeriods(long effectivePeriods){
		this.effectivePeriods = effectivePeriods;
	}

	public int getEffectiveMinTransactionsPerPeriod(){
		return effectiveMinTransactionsPerPeriod;
	}

	public void setEffectiveMinTransactionsPerPeriod(int effectiveMinTransactionsPerPeriod){
		this.effectiveMinTransactionsPerPeriod = effectiveMinTransactionsPerPeriod;
	}

	public int getEffectiveMaxTransactionsPerPeriod(){
		return effectiveMaxTransactionsPerPeriod;
	}

	public void setEffectiveMaxTransactionsPerPeriod(int effectiveMaxTransactionsPerPeriod){
		this.effectiveMaxTransactionsPerPeriod = effectiveMaxTransactionsPerPeriod;
	}

	public double getEffectiveAverageTransactionsPerPeriod(){
		return effectiveAverageTransactionsPerPeriod;
	}

	public void setEffectiveAverageTransactionsPerPeriod(double effectiveAverageTransactionsPerPeriod){
		this.effectiveAverageTransactionsPerPeriod = effectiveAverageTransactionsPerPeriod;
	}

	public double getAverageTransactionsPerPeriod(){
		return averageTransactionsPerPeriod;
	}

	public void setAverageTransactionsPerPeriod(double averageTransactionsPerPeriod){
		this.averageTransactionsPerPeriod = averageTransactionsPerPeriod;
	}

	public long getCurrentPeriodStartTime(){
		return currentPeriodStartTime;
	}

	public void setCurrentPeriodStartTime(long currentPeriodStartTime){
		this.currentPeriodStartTime = currentPeriodStartTime;
	}

	public long getCurrentPeriodStopTime(){
		return currentPeriodStopTime;
	}

	public void setCurrentPeriodStopTime(long currentPeriodStopTime){
		this.currentPeriodStopTime = currentPeriodStopTime;
	}

	public int getCurrentThroughput(){
		return currentThroughput;
	}

	public void setCurrentThroughput(int currentThroughput){
		this.currentThroughput = currentThroughput;
	}

	public int getCurrentTransactionsPerPeriod(){
		return currentTransactionsPerPeriod;
	}

	public void setCurrentTransactionsPerPeriod(int currentTransactionsPerPeriod){
		this.currentTransactionsPerPeriod = currentTransactionsPerPeriod;
	}
}
