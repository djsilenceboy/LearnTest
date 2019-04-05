
package com.djs.learn.ctc.transaction;

import java.io.Serializable;

import com.djs.learn.ctc.utility.callback.SimpleCallbackInterfaceTemplate;

/**
 * Basic transaction control info DTO.
 */
public class BasicTransactionControlInfoDto implements Cloneable, Serializable
{
	private static final long serialVersionUID = 8113278858977848553L;

	/**
	 * Internal ID.
	 * <p>
	 * For debugging/logging purpose.<br>
	 * May be unique or not.
	 */
	protected String internalId = "NoId";

	/**
	 * Maximum throughput.
	 * <p>
	 * Should > 0.
	 */
	protected int maximumThroughput = -1;
	/**
	 * Maximum transactions per period.
	 * <p>
	 * Should > 0.
	 */
	protected int maximumTransactionsPerPeriod = -1;
	/**
	 * Transaction period time.
	 * <p>
	 * In millisecond. Should > 0.
	 */
	protected long transactionPeriodTime = -1;

	/**
	 * Control start time.
	 * <p>
	 * In millisecond.
	 */
	protected long controlStartTime = -1;
	/**
	 * Control stop time.
	 * <p>
	 * In millisecond.
	 */
	protected long controlStopTime = -1;
	/**
	 * Control duration time.
	 * <p>
	 * In millisecond. Total time for all transactions.
	 */
	protected long controlDurationTime = -1;

	/**
	 * Total transactions.
	 * <p>
	 * It is an Array [5].<br>
	 * Element[0] for total.<br>
	 * Element[1,4] for BasicTransactionInfoDto.TransactionResultStatus by order.
	 *
	 * @see BasicTransactionInfoDto
	 */
	protected BasicTotalTransactionInfoDto[] totalTransactionInfoDtos = new BasicTotalTransactionInfoDto[]{new BasicTotalTransactionInfoDto(),
	                                                                                                       new BasicTotalTransactionInfoDto(),
	                                                                                                       new BasicTotalTransactionInfoDto(),
	                                                                                                       new BasicTotalTransactionInfoDto(),
	                                                                                                       new BasicTotalTransactionInfoDto()};
	/**
	 * Total transaction periods.
	 * <p>
	 * It is an Array [2].<br>
	 * Element[0] for total: counting of all periods.<br>
	 * Element[1] for effective: counting of periods with at least one transaction.
	 */
	protected long[] totalTransactionPeriods = new long[]{0, 0};
	/**
	 * Average number of transactions per period.
	 * <p>
	 * It is an Array [2].<br>
	 * Element[0] for total: counting of all periods.<br>
	 * Element[1] for effective: counting of periods with at least one transaction.
	 */
	protected double[] averageTransactionsPerPeriod = new double[]{0.0, 0.0};
	/**
	 * Effective minimum number of transactions per period.
	 * <p>
	 * Initialized as a very big number.
	 */
	protected int effectiveMinimumTransactionsPerPeriod = 1000000;
	/**
	 * Effective maximum number of transactions per period.
	 * <p>
	 * Initialized as a very small number.
	 */
	protected int effectiveMaximumTransactionsPerPeriod = 0;

	/**
	 * Maximum used throughput.
	 * <p>
	 * Count every time there is throughput changes.
	 */
	protected int maximumUsedThroughput = 0;
	/**
	 * Total issued tokens.
	 * <p>
	 * Count every time there is a token issued.
	 */
	protected long totalIssuedTokens = 0;
	/**
	 * Total revoked tokens.
	 * <p>
	 * Count every time there is a token revoked.
	 */
	protected long totalRevokedTokens = 0;
	/**
	 * Total released tokens.
	 * <p>
	 * Count every time there is a token released.
	 */
	protected long totalReleasedTokens = 0;
	/**
	 * Total rejected token requests due to reaching maximum transactions per period.
	 * <p>
	 * Count every time there is a token request rejected.
	 */
	protected long totalRejectedTokenRequestsByMaximumTransactions = 0;
	/**
	 * Total rejected token requests due to reaching maximum throughput.
	 * <p>
	 * Count every time there is a token request rejected.
	 */
	protected long totalRejectedTokenRequestsByMaximumThroughput = 0;

	/**
	 * Number of used throughput.
	 * <p>
	 * The used throughput will be released after the transaction is over.
	 */
	protected int usedThroughput = 0;
	/**
	 * Current transaction period info DTO.
	 * <p>
	 * In millisecond.
	 */
	protected BasicTransactionPeriodInfoDto currentTransactionPeriodInfoDto = null;
	/**
	 * Callback to inform current/latest transaction period is over.
	 * <p>
	 * Can be null, means no callback.<br>
	 * User's callback method should not block for long time.
	 */
	protected SimpleCallbackInterfaceTemplate<BasicTransactionPeriodInfoDto, Object> transactionPeriodInfoCallback = null;

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

	public int getMaximumThroughput(){
		return maximumThroughput;
	}

	public void setMaximumThroughput(int maximumThroughput){
		this.maximumThroughput = maximumThroughput;
	}

	public int getMaximumTransactionsPerPeriod(){
		return maximumTransactionsPerPeriod;
	}

	public void setMaximumTransactionsPerPeriod(int maximumTransactionsPerPeriod){
		this.maximumTransactionsPerPeriod = maximumTransactionsPerPeriod;
	}

	public long getTransactionPeriodTime(){
		return transactionPeriodTime;
	}

	public void setTransactionPeriodTime(long transactionPeriodTime){
		this.transactionPeriodTime = transactionPeriodTime;
	}

	public long getControlStartTime(){
		return controlStartTime;
	}

	public void setControlStartTime(long controlStartTime){
		this.controlStartTime = controlStartTime;
	}

	public long getControlStopTime(){
		return controlStopTime;
	}

	public void setControlStopTime(long controlStopTime){
		this.controlStopTime = controlStopTime;
	}

	public long getControlDurationTime(){
		return controlDurationTime;
	}

	public void setControlDurationTime(long controlDurationTime){
		this.controlDurationTime = controlDurationTime;
	}

	public BasicTotalTransactionInfoDto[] getTotalTransactionInfoDtos(){
		return totalTransactionInfoDtos;
	}

	public void setTotalTransactionInfoDtos(BasicTotalTransactionInfoDto[] totalTransactionInfoDtos){
		this.totalTransactionInfoDtos = totalTransactionInfoDtos;
	}

	public long[] getTotalTransactionPeriods(){
		return totalTransactionPeriods;
	}

	public void setTotalTransactionPeriods(long[] totalTransactionPeriods){
		this.totalTransactionPeriods = totalTransactionPeriods;
	}

	public double[] getAverageTransactionsPerPeriod(){
		return averageTransactionsPerPeriod;
	}

	public void setAverageTransactionsPerPeriod(double[] averageTransactionsPerPeriod){
		this.averageTransactionsPerPeriod = averageTransactionsPerPeriod;
	}

	public int getEffectiveMinimumTransactionsPerPeriod(){
		return effectiveMinimumTransactionsPerPeriod;
	}

	public void setEffectiveMinimumTransactionsPerPeriod(int effectiveMinimumTransactionsPerPeriod){
		this.effectiveMinimumTransactionsPerPeriod = effectiveMinimumTransactionsPerPeriod;
	}

	public int getEffectiveMaximumTransactionsPerPeriod(){
		return effectiveMaximumTransactionsPerPeriod;
	}

	public void setEffectiveMaximumTransactionsPerPeriod(int effectiveMaximumTransactionsPerPeriod){
		this.effectiveMaximumTransactionsPerPeriod = effectiveMaximumTransactionsPerPeriod;
	}

	public int getMaximumUsedThroughput(){
		return maximumUsedThroughput;
	}

	public void setMaximumUsedThroughput(int maximumUsedThroughput){
		this.maximumUsedThroughput = maximumUsedThroughput;
	}

	public long getTotalIssuedTokens(){
		return totalIssuedTokens;
	}

	public void setTotalIssuedTokens(long totalIssuedTokens){
		this.totalIssuedTokens = totalIssuedTokens;
	}

	public long getTotalRevokedTokens(){
		return totalRevokedTokens;
	}

	public void setTotalRevokedTokens(long totalRevokedTokens){
		this.totalRevokedTokens = totalRevokedTokens;
	}

	public long getTotalReleasedTokens(){
		return totalReleasedTokens;
	}

	public void setTotalReleasedTokens(long totalReleasedTokens){
		this.totalReleasedTokens = totalReleasedTokens;
	}

	public long getTotalRejectedTokenRequestsByMaximumTransactions(){
		return totalRejectedTokenRequestsByMaximumTransactions;
	}

	public void setTotalRejectedTokenRequestsByMaximumTransactions(long totalRejectedTokenRequestsByMaximumTransactions){
		this.totalRejectedTokenRequestsByMaximumTransactions = totalRejectedTokenRequestsByMaximumTransactions;
	}

	public long getTotalRejectedTokenRequestsByMaximumThroughput(){
		return totalRejectedTokenRequestsByMaximumThroughput;
	}

	public void setTotalRejectedTokenRequestsByMaximumThroughput(long totalRejectedTokenRequestsByMaximumThroughput){
		this.totalRejectedTokenRequestsByMaximumThroughput = totalRejectedTokenRequestsByMaximumThroughput;
	}

	public int getUsedThroughput(){
		return usedThroughput;
	}

	public void setUsedThroughput(int usedThroughput){
		this.usedThroughput = usedThroughput;
	}

	public BasicTransactionPeriodInfoDto getCurrentTransactionPeriodInfoDto(){
		return currentTransactionPeriodInfoDto;
	}

	public void setCurrentTransactionPeriodInfoDto(BasicTransactionPeriodInfoDto currentTransactionPeriodInfoDto){
		this.currentTransactionPeriodInfoDto = currentTransactionPeriodInfoDto;
	}

	public SimpleCallbackInterfaceTemplate<BasicTransactionPeriodInfoDto, Object> getTransactionPeriodInfoCallback(){
		return transactionPeriodInfoCallback;
	}

	public void setTransactionPeriodInfoCallback(SimpleCallbackInterfaceTemplate<BasicTransactionPeriodInfoDto, Object> transactionPeriodInfoCallback){
		this.transactionPeriodInfoCallback = transactionPeriodInfoCallback;
	}
}
