
package com.djs.learn.ctc.transaction;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.djs.learn.ctc.utility.CommonDateTimeHelper;
import com.djs.learn.ctc.utility.CommonJavaHelper;
import com.djs.learn.ctc.utility.CommonToStringHelper;
import com.djs.learn.ctc.utility.IdGenerationHelper;
import com.djs.learn.ctc.utility.callback.SimpleCallbackInterfaceTemplate;

/**
 * Basic transaction control helper.
 * <p>
 * After startControl() and stopControl() once for one instance of this class, do not reuse it. Create and use a new instance.
 * <p>
 * Normal sequence:<br>
 * <ol>
 * <li>setTransactionThreshold()
 * <li>setTransactionPeriodInfoCallback(), optional
 * <li>startControl()
 * <li>checkCurrentTransactionPeriod()
 * <li>requestTransactionToken()
 * <li>releaseTransactionToken()
 * <li>(back to step 4: checkCurrentTransactionPeriod().)
 * <li>stopControl()
 * </ol>
 * <p>
 */
public class BasicTransactionControlHelper
{
	private static final Logger log = LogManager.getLogger(BasicTransactionControlHelper.class);

	/**
	 * ID.
	 * <p>
	 * For internal usage only. <br>
	 * Default value is "null".
	 */
	protected String id = null;

	/**
	 * Transaction control info DTO.
	 */
	protected BasicTransactionControlInfoDto transactionControlInfoDto = null;
	/**
	 * Current transaction periodInfo DTO.
	 */
	protected BasicTransactionPeriodInfoDto currentTransactionPeriodInfoDto = null;

	/**
	 * Flag: if control has been started once.
	 * <p>
	 * set true in startControl().
	 */
	protected boolean controlStarted = false;
	/**
	 * Flag: if control has been stopped once.
	 * <p>
	 * set true in stopControl().
	 */
	protected boolean controlStopped = false;
	/**
	 * Log ID for this instance.
	 */
	protected String logId = null;

	/**
	 * Token ID counter.
	 * <p>
	 * It will start from 1, and loop back to 1 while reach Long.MAX_VALUE.<br>
	 * It will convert to String type as tokenId.
	 */
	protected long tokenIdCounter = 0;
	/**
	 * Transaction info DTOs.
	 * <p>
	 * Issued with TOKEN_STATUS_AVAILABLE, and not yet released ones.
	 */
	protected Map<String, BasicTransactionInfoDto> transactionInfoDtos = Collections.synchronizedSortedMap(new TreeMap<String, BasicTransactionInfoDto>());

	/**
	 * Constructor.
	 *
	 * @param id
	 *        String
	 */
	public BasicTransactionControlHelper(String id){
		if (id == null) {
			id = Short.toString(IdGenerationHelper.generate16bIdByTime(true));
		}

		logId = "[" + id + "] ";

		if (log.isTraceEnabled()) {
			log.trace(logId + "Enter...");
		}

		this.id = id;

		currentTransactionPeriodInfoDto = new BasicTransactionPeriodInfoDto();
		currentTransactionPeriodInfoDto.setInternalId(id);

		transactionControlInfoDto = new BasicTransactionControlInfoDto();
		transactionControlInfoDto.setInternalId(id);
		transactionControlInfoDto.setCurrentTransactionPeriodInfoDto(currentTransactionPeriodInfoDto);
	}

	public String getId(){
		return id;
	}

	/**
	 * Check if control started.
	 *
	 * @return boolean
	 */
	public synchronized boolean isControlStarted(){
		return controlStarted;
	}

	/**
	 * Check if control stopped.
	 *
	 * @return boolean
	 */
	public synchronized boolean isControlStopped(){
		return controlStopped;
	}

	/**
	 * Set transaction threshold.
	 * <p>
	 * Call this method:<br>
	 * 1. Before startControl() with initial values.<br>
	 * 2. During runtime to updated values.
	 *
	 * @param maximumThroughput
	 *        int, > 0.
	 * @param maximumTransactionsPerPeriod
	 *        int, > 0.
	 * @param transactionPeriodTime
	 *        long, > 0.
	 * @throws Exception
	 */
	public synchronized void setTransactionThreshold(int maximumThroughput, int maximumTransactionsPerPeriod, long transactionPeriodTime) throws Exception{
		if (maximumThroughput > 0) {
			transactionControlInfoDto.setMaximumThroughput(maximumThroughput);
		} else {
			throw new Exception("Maximum throughout must > 0.");
		}

		if (maximumTransactionsPerPeriod > 0) {
			transactionControlInfoDto.setMaximumTransactionsPerPeriod(maximumTransactionsPerPeriod);
		} else {
			throw new Exception("Maximum transactions per period must > 0.");
		}

		if (transactionPeriodTime > 0) {
			transactionControlInfoDto.setTransactionPeriodTime(transactionPeriodTime);
		} else {
			throw new Exception("Transaction period time must > 0.");
		}

		if (log.isDebugEnabled()) {
			log.debug(logId + "Maximum throughput = " + transactionControlInfoDto.getMaximumThroughput());
			log.debug(logId + "Maximum transactions per period = " + transactionControlInfoDto.getMaximumTransactionsPerPeriod());
			log.debug(logId + "Transaction period time = " + transactionControlInfoDto.getTransactionPeriodTime());
		}
	}

	/**
	 * Set transaction period info callback.
	 * <p>
	 * For just Completed period only! <br>
	 * Call this method:<br>
	 * 1. Before startControl() with initial values.<br>
	 * 2. During runtime.
	 *
	 * @param transactionPeriodInfoCallback
	 *        SimpleCallbackInterfaceTemplate<BasicTransactionPeriodInfoDto, Object>.<br>
	 *        Can be null, means turn off existing callback if any.
	 */
	public synchronized void setTransactionPeriodInfoCallback(SimpleCallbackInterfaceTemplate<BasicTransactionPeriodInfoDto, Object> transactionPeriodInfoCallback){
		transactionControlInfoDto.setTransactionPeriodInfoCallback(transactionPeriodInfoCallback);

		if (log.isDebugEnabled()) {
			log.debug(logId + "Callback turn " + (transactionPeriodInfoCallback == null ? "off" : "on") + ".");
		}
	}

	/**
	 * Start control.
	 * <p>
	 * Call setTransactionThreshold() before starting control.
	 *
	 * @throws Exception
	 *         - Normally, there should be no exception.
	 */
	public void startControl() throws Exception{
		// Start time, a multiple of TransactionPeriodTime.
		startControl((System.currentTimeMillis() / transactionControlInfoDto.getTransactionPeriodTime())
		        * transactionControlInfoDto.getTransactionPeriodTime());
	}

	/**
	 * Start control with specified start time.
	 * <p>
	 * Call setTransactionThreshold() before starting control.<br>
	 * This method is normally used to synchronize with other already running copy.
	 *
	 * @param time
	 *        long, in milliseconds. "< 0" mean current time.<br>
	 *        It should not be later than current time.
	 * @throws Exception
	 *         - Normally, there should be no exception.
	 */
	public synchronized void startControl(long time) throws Exception{
		if (controlStopped) {
			throw new Exception("Control has been stopped, do not reuse this instance.");
		} else if (controlStarted) {
			throw new Exception("Control has been started.");
		}

		long currentTime = System.currentTimeMillis();

		if (time > currentTime) {
			throw new Exception("The specified time " + time + " is larger than current time " + currentTime + ".");
		} else if (time < 0) {
			time = currentTime;
		}

		// Start time.
		transactionControlInfoDto.setControlStartTime(time);

		if (log.isInfoEnabled()) {
			log.info(logId + "Control start time (ms) = " + transactionControlInfoDto.getControlStartTime());

			log.info(logId + "Transaction control info = "
			        + CommonToStringHelper
			                .objectDeepToString_2_Readable(transactionControlInfoDto, null,
			                                               new String[]{"usedThroughput", "currentTransactionPeriodInfoDto", "transactionPeriodInfoCallback"},
			                                               null, -1, 500));
		}

		// Control start time is also the start time of first period.
		initCurrentTransactionPeriod(transactionControlInfoDto.getControlStartTime());

		controlStarted = true;
	}

	/**
	 * Stop control.
	 *
	 * @throws Exception
	 *         - Normally, there should be no exception.
	 */
	public synchronized void stopControl() throws Exception{
		if (controlStopped) {
			throw new Exception("Control has been stopped.");
		} else if (!controlStarted) {
			throw new Exception("Control has not been started yet.");
		}

		// Last period.
		countCurrentTransactionPeriod();

		countRuntimeTransactionControlInfo();

		if (log.isInfoEnabled()) {
			log.info(logId + "Control stop time (ms) = " + transactionControlInfoDto.getControlStopTime());
			log.info(logId + "Control duration time (ms) = " + transactionControlInfoDto.getControlDurationTime());

			log.info(logId + "Transaction control info = "
			        + CommonToStringHelper
			                .objectDeepToString_2_Readable(transactionControlInfoDto, null,
			                                               new String[]{"usedThroughput", "currentTransactionPeriodInfoDto", "transactionPeriodInfoCallback"},
			                                               null, -1, 500));
		}

		controlStopped = true;
	}

	/**
	 * Get TransactionControlInfoDto.
	 * <p>
	 * If control has been started, the method will count runtime info first.
	 *
	 * @return BasicTransactionControlInfoDto - A clone of internal instance.
	 */
	public synchronized BasicTransactionControlInfoDto getTransactionControlInfoDto(){
		if (controlStarted && !controlStopped) {
			countRuntimeTransactionControlInfo();
		}

		return (BasicTransactionControlInfoDto)transactionControlInfoDto.clone();
	}

	/**
	 * Get current TransactionPeriodInfoDto.
	 *
	 * @return BasicTransactionPeriodInfoDto - A clone of internal instance.
	 */
	public synchronized BasicTransactionPeriodInfoDto getCurrentTransactionPeriodInfoDto(){
		return (BasicTransactionPeriodInfoDto)currentTransactionPeriodInfoDto.clone();
	}

	/**
	 * Count transaction control info in runtime.
	 * <p>
	 * Normally for debug purpose.
	 */
	protected void countRuntimeTransactionControlInfo(){
		transactionControlInfoDto.setControlStopTime(System.currentTimeMillis());
		transactionControlInfoDto
		        .setControlDurationTime((transactionControlInfoDto.getControlStopTime() - transactionControlInfoDto.getControlStartTime()) + 1);

		// Count for total, Element[0].
		transactionControlInfoDto.getTotalTransactionPeriods()[0] = (long)(((double)transactionControlInfoDto.getControlDurationTime()
		        / transactionControlInfoDto.getTransactionPeriodTime()) + 0.5);

		// For very rare case while stopControl(), effective periods may be 1 more than total!
		// Because effective is actually added up, while total is calculated by dividing.
		if (transactionControlInfoDto.getTotalTransactionPeriods()[1] > transactionControlInfoDto.getTotalTransactionPeriods()[0]) {
			transactionControlInfoDto.getTotalTransactionPeriods()[0] = transactionControlInfoDto.getTotalTransactionPeriods()[1];
		}

		//

		BasicTotalTransactionInfoDto[] totalTransactionInfoDtos = transactionControlInfoDto.getTotalTransactionInfoDtos();

		// If there has been any transaction.
		if (totalTransactionInfoDtos[0].getTotalTransactions() > 0) {
			// Count for total, Element[0].
			if (transactionControlInfoDto.getTotalTransactionPeriods()[0] > 0) {
				transactionControlInfoDto.getAverageTransactionsPerPeriod()[0] = Math
				        .round(((double)totalTransactionInfoDtos[0].getTotalTransactions() / transactionControlInfoDto.getTotalTransactionPeriods()[0]) * 100.0)
				        / 100.0;
			}

			// Count for total effective, Element[1].
			if (transactionControlInfoDto.getTotalTransactionPeriods()[1] > 0) {
				transactionControlInfoDto.getAverageTransactionsPerPeriod()[1] = Math
				        .round(((double)totalTransactionInfoDtos[0].getTotalTransactions() / transactionControlInfoDto.getTotalTransactionPeriods()[1]) * 100.0)
				        / 100.0;
			}
		}

		for (int i = 0; i < totalTransactionInfoDtos.length; i++) {
			if (totalTransactionInfoDtos[i].getTotalTransactions() > 0) {
				totalTransactionInfoDtos[i].setAverageTransactionDurationTime(Math
				        .round((totalTransactionInfoDtos[i].getTotalTransactionDurationTime() / totalTransactionInfoDtos[i].getTotalTransactions()) * 100.0)
				        / 100.0);
			}
		}
	}

	/**
	 * Initialize current transaction period.
	 *
	 * @param time
	 *        long, in milliseconds. "< 0" mean current time.
	 */
	protected void initCurrentTransactionPeriod(long time){
		currentTransactionPeriodInfoDto.setTransactionPeriodStartTime((time >= 0) ? time : System.currentTimeMillis());
		currentTransactionPeriodInfoDto.setTransactionPeriodStopTime((currentTransactionPeriodInfoDto.getTransactionPeriodStartTime()
		        + transactionControlInfoDto.getTransactionPeriodTime()) - 1);
		currentTransactionPeriodInfoDto.setUsedTransactions(0);

		if (log.isTraceEnabled()) {
			log.trace(logId + "Current transaction period info = "
			        + CommonToStringHelper.objectDeepToString_0_ValueOnly(currentTransactionPeriodInfoDto, null, null, null, 0, 0));
		}
	}

	/**
	 * Get remained time for current transaction period.
	 *
	 * @return long - Time in milliseconds. The value may be negative, if current period has been over and no new period begin!
	 */
	public synchronized long getRemainedTimeForCurrentTransactionPeriod(){
		return currentTransactionPeriodInfoDto.getTransactionPeriodStopTime() - System.currentTimeMillis();
	}

	/**
	 * Count current transaction period.
	 * <p>
	 * It will count to total if there is used transactions in current transaction period.<br>
	 * "current" may be not now, but "latest".
	 */
	protected void countCurrentTransactionPeriod(){
		int usedTransactions = currentTransactionPeriodInfoDto.getUsedTransactions();

		if (usedTransactions > 0) {
			if (log.isTraceEnabled()) {
				log.trace(logId + "Completed transaction period info = "
				        + CommonToStringHelper.objectDeepToString_0_ValueOnly(currentTransactionPeriodInfoDto, null, null, null, 0, 0));
			}

			// If it is new minimum value.
			if (transactionControlInfoDto.getEffectiveMinimumTransactionsPerPeriod() > usedTransactions) {
				transactionControlInfoDto.setEffectiveMinimumTransactionsPerPeriod(usedTransactions);
			}

			// If it is new maximum value.
			if (transactionControlInfoDto.getEffectiveMaximumTransactionsPerPeriod() < usedTransactions) {
				transactionControlInfoDto.setEffectiveMaximumTransactionsPerPeriod(usedTransactions);
			}

			// Count for total effective, Element[1].
			transactionControlInfoDto.getTotalTransactionPeriods()[1]++;

			// Callback.
			if (transactionControlInfoDto.getTransactionPeriodInfoCallback() != null) {
				try {
					// There would be Exception. But it is not expected, so will be ignored.
					transactionControlInfoDto.getTransactionPeriodInfoCallback().invoke((BasicTransactionPeriodInfoDto)currentTransactionPeriodInfoDto.clone());
				} catch (Exception e) {
					if (log.isErrorEnabled()) {
						log.error(logId + "Callback failed. Ignored Exception = " + e, e);
					}
				}
			}
		}
	}

	/**
	 * Count one used throughput.
	 *
	 * @throws Exception
	 */
	protected void countUsedThroughput() throws Exception{
		int newUsedThroughput = transactionControlInfoDto.getUsedThroughput() + 1;
		int maximumThroughput = transactionControlInfoDto.getMaximumThroughput();

		if (log.isTraceEnabled()) {
			log.trace(logId + "Used throughput += " + newUsedThroughput + " / " + maximumThroughput);
		}

		if (newUsedThroughput > maximumThroughput) {
			throw new Exception("Maximum of used throughput should <= " + maximumThroughput + ".");
		}

		transactionControlInfoDto.setUsedThroughput(newUsedThroughput);
	}

	/**
	 * Release/Revoke one used throughput.
	 *
	 * @throws Exception
	 */
	protected void releaseUsedThroughput() throws Exception{
		int newUsedThroughput = transactionControlInfoDto.getUsedThroughput() - 1;

		if (log.isTraceEnabled()) {
			log.trace(logId + "Used throughput -= " + newUsedThroughput + " / " + transactionControlInfoDto.getMaximumThroughput());
		}

		if (newUsedThroughput < 0) {
			throw new Exception("Minimum of used throughput should >= 0.");
		}

		transactionControlInfoDto.setUsedThroughput(newUsedThroughput);
	}

	/**
	 * Count one used transaction for current transaction period.
	 *
	 * @throws Exception
	 */
	protected void countUsedTransactionForCurrentTransactionPeriod() throws Exception{
		int newUsedTransactions = currentTransactionPeriodInfoDto.getUsedTransactions() + 1;
		int maximumTransactionsPerPeriod = transactionControlInfoDto.getMaximumTransactionsPerPeriod();

		if (log.isTraceEnabled()) {
			log.trace(logId + "Used transactions += " + newUsedTransactions + " / " + maximumTransactionsPerPeriod);
		}

		if (newUsedTransactions > maximumTransactionsPerPeriod) {
			throw new Exception("Maximum of used transactions for current transaction period should <= " + maximumTransactionsPerPeriod + ".");
		}

		currentTransactionPeriodInfoDto.setUsedTransactions(newUsedTransactions);
	}

	/**
	 * Release/Revoke one used transaction for current transaction period.
	 *
	 * @throws Exception
	 */
	protected void releaseUsedTransactionForCurrentTransactionPeriod() throws Exception{
		int newUsedTransactions = currentTransactionPeriodInfoDto.getUsedTransactions() - 1;

		if (newUsedTransactions < 0) {
			if (log.isDebugEnabled()) {
				log.debug(logId + "Current transaction period has been over. It cannot release the used transaction. But it is ok.");
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace(logId + "Used transactions -= " + newUsedTransactions + " / " + transactionControlInfoDto.getMaximumTransactionsPerPeriod());
			}

			currentTransactionPeriodInfoDto.setUsedTransactions(newUsedTransactions);
		}
	}

	/**
	 * Check current transaction period.
	 * <p>
	 * It will count to total if there is used transactions in current transaction period.<br>
	 * "current" may be not now, but "latest".
	 *
	 * @throws Exception
	 *         - If check time point is earlier than current transaction period. Should never happen.
	 */
	public synchronized void checkCurrentTransactionPeriod() throws Exception{
		long checkTimePoint = System.currentTimeMillis();

		// Check time point should never be earlier than current transaction period!
		if (checkTimePoint < currentTransactionPeriodInfoDto.getTransactionPeriodStartTime()) {
			throw new Exception("Check time point (" + checkTimePoint + ") < Current period transaction start time ("
			        + currentTransactionPeriodInfoDto.getTransactionPeriodStartTime() + ").");
		}

		// If check time point is after current transaction period, that means:
		// 1. Current/latest transaction period has been over;
		// 2. New transaction for new transaction period comes.
		// If current/latest transaction period has not counted yet,
		// 1. Count that period;
		// 2. Reset for new period.
		if (checkTimePoint > currentTransactionPeriodInfoDto.getTransactionPeriodStopTime()) {
			// Calculate time gap.
			long passedTimeForNewPeriod = (checkTimePoint - currentTransactionPeriodInfoDto.getTransactionPeriodStopTime())
			        % transactionControlInfoDto.getTransactionPeriodTime();
			long newCurrentTransactionStartTime = (checkTimePoint - passedTimeForNewPeriod) + 1;

			if (currentTransactionPeriodInfoDto.getUsedTransactions() > 0) {
				countCurrentTransactionPeriod();
			}

			initCurrentTransactionPeriod(newCurrentTransactionStartTime);
		}
	}

	/**
	 * Get token ID.
	 *
	 * @return String - Long in Hex string, not null.
	 */
	protected String getTokenId(){
		tokenIdCounter++;

		// Format to 16 character HEX string.
		// From "0000000000000001" to "7FFFFFFFFFFFFFFF".
		String tokenId = CommonJavaHelper.convertNumberToHexString(16, true, true, tokenIdCounter);

		if (tokenIdCounter == Long.MAX_VALUE) {
			tokenIdCounter = 0;
		}

		return tokenId;
	}

	/**
	 * Request transaction token (included in transaction info DTO).
	 * <p>
	 * Always call checkCurrentTransactionPeriod() before this method.
	 * <p>
	 * If get a token, call releaseTransactionToken() to release it after transaction processed.<br>
	 * If token is not available due to reaching maximum transactions per period, getRemainedTimeForCurrentTransactionPeriod() and sleep.<br>
	 * If token is not available due to reaching maximum throughput, sleep for a while and try this method again.
	 * <p>
	 * User can use BasicTransactionInfoDto for other transaction related management. The class will also keep a reference to this copy.
	 *
	 * @return BasicTransactionInfoDto - Always check its token status first, BasicTransactionInfoDto.TOKEN_STATUS_XXX.
	 * @throws Exception
	 */
	public synchronized BasicTransactionInfoDto requestTransactionToken() throws Exception{
		if (controlStopped) {
			throw new Exception("Control has been stopped.");
		} else if (!controlStarted) {
			throw new Exception("Control has not been started yet.");
		}

		BasicTransactionInfoDto transactionInfoDto = new BasicTransactionInfoDto();
		transactionInfoDto.setInternalId(transactionControlInfoDto.getInternalId());

		// Token is available when used transactions does not exceed maximum value and there is unused throughput.
		if (currentTransactionPeriodInfoDto.getUsedTransactions() >= transactionControlInfoDto.getMaximumTransactionsPerPeriod()) {
			transactionControlInfoDto
			        .setTotalRejectedTokenRequestsByMaximumTransactions(transactionControlInfoDto.getTotalRejectedTokenRequestsByMaximumTransactions() + 1);

			transactionInfoDto.setTokenStatus(TransactionToken.MAX_TRANSACTIONS);
		} else if (transactionControlInfoDto.getUsedThroughput() >= transactionControlInfoDto.getMaximumThroughput()) {
			transactionControlInfoDto
			        .setTotalRejectedTokenRequestsByMaximumThroughput(transactionControlInfoDto.getTotalRejectedTokenRequestsByMaximumThroughput() + 1);

			transactionInfoDto.setTokenStatus(TransactionToken.MAX_THROUGHPUT);
		} else {
			// There would be Exception.
			countUsedThroughput();

			if (transactionControlInfoDto.getUsedThroughput() > transactionControlInfoDto.getMaximumUsedThroughput()) {
				transactionControlInfoDto.setMaximumUsedThroughput(transactionControlInfoDto.getUsedThroughput());
			}

			transactionControlInfoDto.setTotalIssuedTokens(transactionControlInfoDto.getTotalIssuedTokens() + 1);

			// There would be Exception.
			countUsedTransactionForCurrentTransactionPeriod();

			//

			transactionInfoDto.setTokenStatus(TransactionToken.AVAILABLE);
			transactionInfoDto.setTokenId(getTokenId());
			transactionInfoDto.setTokenIssueTime(CommonDateTimeHelper.getUtcTime());
			transactionInfoDto.setTransactionStatus(TransactionStatus.START);
			transactionInfoDto.setTransactionStartTime(transactionInfoDto.getTokenIssueTime());

			//

			transactionInfoDtos.put(transactionInfoDto.getTokenId(), transactionInfoDto);
		}

		if (log.isTraceEnabled()) {
			StringBuilder temp = new StringBuilder();

			temp.append(logId);
			temp.append("Transaction info DTO = ");
			temp.append(transactionInfoDto.getTokenStatus().getDescription());
			temp.append(";");
			temp.append(CommonToStringHelper.objectDeepToString_2_Readable(transactionInfoDto, null, null, null, 0, 0));

			log.trace(temp.toString());
		}

		return transactionInfoDto;
	}

	/**
	 * Revoke transaction token (included in transaction info DTO).
	 * <p>
	 * Call this method to revoke unused token got by requestTransactionToken().
	 * <p>
	 * User should not use BasicTransactionInfoDto for other transaction related management any more.
	 *
	 * @throws Exception
	 */
	public void revokeTransactionToken(BasicTransactionInfoDto transactionInfoDto) throws Exception{
		if (log.isTraceEnabled()) {
			StringBuilder temp = new StringBuilder();

			temp.append(logId);
			temp.append("Transaction info DTO = ");
			temp.append(CommonToStringHelper.objectDeepToString_2_Readable(transactionInfoDto, null, null, null, 0, 0));

			log.trace(temp.toString());
		}

		if ((transactionInfoDto.getInternalId() == null) || !transactionInfoDto.getInternalId().equals(transactionControlInfoDto.getInternalId())) {
			throw new Exception("This token is not issued by this instance.");
		} else if (transactionInfoDto.getTokenStatus() != TransactionToken.AVAILABLE) {
			throw new Exception("Wrong token status, no need to revoke.");
		} else if (transactionInfoDto.getTokenId() == null) {
			throw new Exception("Token ID is null.");
		}

		synchronized (this) {
			if (controlStopped) {
				throw new Exception("Control has been stopped.");
			} else if (!controlStarted) {
				throw new Exception("Control has not been started yet.");
			} else if (!transactionInfoDtos.containsKey(transactionInfoDto.getTokenId())) {
				throw new Exception("The token may have been revoked, or be invlaid.");
			} else if (transactionInfoDtos.get(transactionInfoDto.getTokenId()) != transactionInfoDto) {
				throw new Exception("Wrong reference copy to same token ID.");
			}

			//

			// There would be Exception.
			releaseUsedThroughput();

			// There would be Exception.
			releaseUsedTransactionForCurrentTransactionPeriod();

			transactionControlInfoDto.setTotalRevokedTokens(transactionControlInfoDto.getTotalRevokedTokens() + 1);

			transactionInfoDtos.remove(transactionInfoDto.getTokenId());

			//

			transactionInfoDto.setTokenReleaseTime(CommonDateTimeHelper.getUtcTime());
			transactionInfoDto.setTokenStatus(TransactionToken.INVALID);
			transactionInfoDto.setTransactionStatus(TransactionStatus.INVALID);

			if (log.isTraceEnabled()) {
				StringBuilder temp = new StringBuilder();

				temp.append(logId);
				temp.append("Transaction info DTO = ");
				temp.append(CommonToStringHelper.objectDeepToString_2_Readable(transactionInfoDto, null, null, null, 0, 0));

				log.trace(temp.toString());
			}
		}
	}

	/**
	 * Release transaction token (included in transaction info DTO).
	 * <p>
	 * Call this method to release token got by requestTransactionToken().
	 * <p>
	 * The method will prepare final transaction time/duration info inside BasicTransactionInfoDto.
	 * <p>
	 * User can use BasicTransactionInfoDto for other transaction related management. The class will remove the reference to this copy.
	 *
	 * @throws Exception
	 */
	public void releaseTransactionToken(BasicTransactionInfoDto transactionInfoDto) throws Exception{
		if (log.isTraceEnabled()) {
			StringBuilder temp = new StringBuilder();

			temp.append(logId);
			temp.append("Transaction info DTO = ");
			temp.append(CommonToStringHelper.objectDeepToString_2_Readable(transactionInfoDto, null, null, null, 0, 0));

			log.trace(temp.toString());
		}

		if ((transactionInfoDto.getInternalId() == null) || !transactionInfoDto.getInternalId().equals(transactionControlInfoDto.getInternalId())) {
			throw new Exception("This token is not issued by this instance.");
		} else if (transactionInfoDto.getTokenStatus() != TransactionToken.AVAILABLE) {
			throw new Exception("Wrong token status, no need to release.");
		} else if (transactionInfoDto.getTokenId() == null) {
			throw new Exception("Token ID is null.");
		}

		synchronized (this) {
			if (controlStopped) {
				throw new Exception("Control has been stopped.");
			} else if (!controlStarted) {
				throw new Exception("Control has not been started yet.");
			} else if (!transactionInfoDtos.containsKey(transactionInfoDto.getTokenId())) {
				throw new Exception("The token may have been released, or be invlaid.");
			} else if (transactionInfoDtos.get(transactionInfoDto.getTokenId()) != transactionInfoDto) {
				throw new Exception("Wrong reference copy to same token ID.");
			}

			// There would be Exception.
			releaseUsedThroughput();

			transactionControlInfoDto.setTotalReleasedTokens(transactionControlInfoDto.getTotalReleasedTokens() + 1);

			transactionInfoDtos.remove(transactionInfoDto.getTokenId());

			//

			transactionInfoDto.setTokenReleaseTime(CommonDateTimeHelper.getUtcTime());
			transactionInfoDto.setTransactionStatus(TransactionStatus.STOP);

			if (transactionInfoDto.getTransactionStartTime() <= 0) {
				transactionInfoDto.setTransactionStartTime(transactionInfoDto.getTokenIssueTime());
			}
			if (transactionInfoDto.getTransactionStopTime() <= 0) {
				transactionInfoDto.setTransactionStopTime(transactionInfoDto.getTokenReleaseTime());
			}
			transactionInfoDto.setTransactionDurationTime((transactionInfoDto.getTransactionStopTime() - transactionInfoDto.getTransactionStartTime()) + 1);

			if (log.isTraceEnabled()) {
				StringBuilder temp = new StringBuilder();

				temp.append(logId);
				temp.append("Transaction info DTO = ");
				temp.append(CommonToStringHelper.objectDeepToString_2_Readable(transactionInfoDto, null, null, null, 0, 0));

				log.trace(temp.toString());
			}

			//

			BasicTotalTransactionInfoDto[] totalTransactionInfoDtos = transactionControlInfoDto.getTotalTransactionInfoDtos();

			{
				// Element[0], for total.
				countTotalTransactionInfo(totalTransactionInfoDtos, 0, transactionInfoDto);

				TransactionResultStatus transactionResultStatus = transactionInfoDto.getTransactionResultStatus();
				int index;

				// Element[2,length-1], for valid status.
				if ((transactionResultStatus == TransactionResultStatus.SUCCEEDED) || (transactionResultStatus == TransactionResultStatus.FAILED)
				        || (transactionResultStatus == TransactionResultStatus.BROKEN)) {
					index = transactionResultStatus.ordinal() + 2;
				}
				// Element[1], for invalid status.
				else {
					index = 1;
				}

				countTotalTransactionInfo(totalTransactionInfoDtos, index, transactionInfoDto);
			}
		}
	}

	/**
	 * Count total transaction info once.
	 *
	 * @param totalTransactionInfoDtos
	 *        BasicTotalTransactionInfoDto []
	 * @param index
	 *        int
	 * @param transactionInfoDto
	 *        BasicTransactionInfoDto
	 */
	protected void countTotalTransactionInfo(BasicTotalTransactionInfoDto[] totalTransactionInfoDtos, int index, BasicTransactionInfoDto transactionInfoDto){
		totalTransactionInfoDtos[index].setTotalTransactions(totalTransactionInfoDtos[index].getTotalTransactions() + 1);
		totalTransactionInfoDtos[index].setTotalTransactionDurationTime(totalTransactionInfoDtos[index].getTotalTransactionDurationTime()
		        + transactionInfoDto.getTransactionDurationTime());

		if (totalTransactionInfoDtos[index].getMaximumTransactionDurationTime() < transactionInfoDto.getTransactionDurationTime()) {
			totalTransactionInfoDtos[index].setMaximumTransactionDurationTime(transactionInfoDto.getTransactionDurationTime());
		}

		if (totalTransactionInfoDtos[index].getMinimumTransactionDurationTime() > transactionInfoDto.getTransactionDurationTime()) {
			totalTransactionInfoDtos[index].setMinimumTransactionDurationTime(transactionInfoDto.getTransactionDurationTime());
		}
	}
}
