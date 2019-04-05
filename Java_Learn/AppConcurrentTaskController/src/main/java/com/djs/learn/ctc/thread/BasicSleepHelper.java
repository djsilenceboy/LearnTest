
package com.djs.learn.ctc.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.djs.learn.ctc.utility.IdGenerationHelper;

/**
 * Basic sleep helper.
 */
public class BasicSleepHelper
{
	private static final Logger log = LogManager.getLogger(BasicSleepHelper.class);

	/**
	 * Default time slice in milliseconds.
	 * <p>
	 * Default value is "1000".<br>
	 * Can be changed by extender.<br>
	 * It should better be >= 15 for Windows system.
	 */
	public static long DEFAULT_TIME_SLICE = 1000; // Milliseconds.
	/**
	 * Default minimal sleep time in milliseconds.
	 * <p>
	 * Default value is "200".<br>
	 * Can be changed by extender.<br>
	 * It should be <= DEFAULT_TIME_SLICE.<br>
	 * It should better be >= 15 for Windows system.
	 */
	public static long DEFAULT_MINIMAL_SLEEP_TIME = 200; // Milliseconds.

	/**
	 * Normal sleep.
	 * <p>
	 * Normal Thread.sleep() and Thread.yield() with Exception.
	 *
	 * @param sleepTime
	 *        long. Sleep time in milliseconds. Should >= 0.<br>
	 *        If > 0, use Thread.sleep();<br>
	 *        If = 0, use Thread.yield() and ignore sleepAtLeast;<br>
	 *        If < 0, no sleep.
	 * @param sleepAtLeast
	 *        boolean. true = sleep at least specified time; false = not sure sleep less or more.
	 * @return long - Actual slept time, milliseconds.
	 * @throws Exception
	 */
	public static long normalSleep(long sleepTime, boolean sleepAtLeast) throws Exception{
		// The ID to correlate log4j info.
		String logId = "[" + IdGenerationHelper.generate16bIdByTime(true) + "] ";

		if (log.isTraceEnabled()) {
			log.trace(logId + "Expect sleep time (ms) = " + sleepTime);
		}

		long sleptTime = 0;

		if (sleepTime > 0) {
			long startTime = System.currentTimeMillis();

			try {
				// There are two ways to do so:
				// 1. Directly call Thread.sleep(). It may sleep less, equal or much.
				// 2. Loop call Thread.sleep(). It may sleep equal or much.
				if (sleepAtLeast) {
					long leftTime = sleepTime;

					while (leftTime > 0) {
						Thread.sleep(leftTime);

						leftTime = sleepTime - (System.currentTimeMillis() - startTime);
					}
				} else {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
				if (log.isErrorEnabled()) {
					log.error(logId + "Sleep failed. Exception = " + e, e);
				}

				throw e;
			} finally {
				sleptTime = System.currentTimeMillis() - startTime;

				if (log.isTraceEnabled()) {
					log.trace(logId + "Actual slept time (ms) = " + sleptTime);
				}
			}
		} else if (sleepTime == 0) {
			Thread.yield();
		}

		return sleptTime;
	}

	/**
	 * Normal sleep.
	 * <p>
	 * Normal Thread.sleep() and Thread.yield() with Exception.<br>
	 * Internally, call normalSleep(?, true).
	 *
	 * @param sleepTime
	 *        long. Sleep time in milliseconds. Should >= 0.<br>
	 *        If > 0, use Thread.sleep();<br>
	 *        If = 0, use Thread.yield() and ignore sleepAtLeast;<br>
	 *        If < 0, no sleep.
	 * @throws Exception
	 */
	public static void normalSleep(long sleepTime) throws Exception{
		// There would be Exception.
		normalSleep(sleepTime, true);
	}

	/**
	 * Simple sleep.
	 * <p>
	 * Normal Thread.sleep() and Thread.yield() but ignoring Exception.<br>
	 * Internally, call normalSleep(?, ?).
	 *
	 * @param sleepTime
	 *        long. Sleep time in milliseconds. Should >= 0.<br>
	 *        If > 0, use Thread.sleep();<br>
	 *        If = 0, use Thread.yield() and ignore sleepAtLeast;<br>
	 *        If < 0, no sleep.
	 * @param sleepAtLeast
	 *        boolean. true = sleep at least specified time; false = not sure sleep less or more.
	 * @return long - Actual slept time, milliseconds.
	 */
	public static long simpleSleep(long sleepTime, boolean sleepAtLeast){
		long sleptTime = 0;
		long startTime = System.currentTimeMillis();

		try {
			// There would be Exception.
			sleptTime = normalSleep(sleepTime, sleepAtLeast);
		} catch (Exception e) {
			sleptTime = System.currentTimeMillis() - startTime;
		}

		return sleptTime;
	}

	/**
	 * Simple sleep.
	 * <p>
	 * Normal Thread.sleep() and Thread.yield() but ignoring Exception.<br>
	 * Internally, call normalSleep(?, true).
	 *
	 * @param sleepTime
	 *        long. Sleep time in milliseconds. Should >= 0.<br>
	 *        If > 0, use Thread.sleep();<br>
	 *        If = 0, use Thread.yield() and ignore sleepAtLeast;<br>
	 *        If < 0, no sleep.
	 */
	public static void simpleSleep(long sleepTime){
		try {
			// There would be Exception.
			normalSleep(sleepTime, true);
		} catch (Exception e) {
		}
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Use the method, when sleep long time and listen to "request quit" signal.
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds. Should >= 0.<br>
	 *        If > 0, use Thread.sleep();<br>
	 *        If = 0, use Thread.yield() and ignore sleepAtLeast;<br>
	 *        If < 0, no sleep.
	 * @param timeSlice
	 *        long. Time slice in milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 * @param minimalSleepTime
	 *        long. Minimal sleep time in milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 * @param sleepAtLeast
	 *        boolean. true = sleep at least specified time; false = not sure sleep less or more.
	 * @return long - Actual slept time, milliseconds.
	 */
	public static long sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime, long timeSlice, long minimalSleepTime,
	                                      boolean sleepAtLeast){
		long sleptTime = 0;

		// If sleep time is too less.
		if (sleepTime <= timeSlice) {
			sleptTime = simpleSleep(sleepTime, sleepAtLeast);
		} else {
			// Minimal sleep time should not be too much.
			if (minimalSleepTime > timeSlice) {
				minimalSleepTime = timeSlice;
			}

			// The ID to correlate log4j info.
			String logId = "[" + IdGenerationHelper.generate16bIdByTime(true) + "] ";

			if (log.isTraceEnabled()) {
				log.trace(logId + "Sleep time (ms) = " + sleepTime);
			}

			long startTime = System.currentTimeMillis();
			long leftTime = sleepTime;
			long adjustTime = 0;

			// Sleep until receiving "request quit" signal, or timeout.
			while (!requestQuitSignalDto.isRequestQuitSignal() && (leftTime > 0)) {
				// If remained time is small: leftTime < timeSlice - adjustTime;
				// Or if after this turn, remained time is small: timeSlice - adjustTime <= leftTime < timeSlice + minimalSleepTime - adjustTime.
				long turnSleepTime = (leftTime < ((timeSlice + minimalSleepTime) - adjustTime)) ? leftTime
				                                                                                : ((timeSlice - adjustTime) < 0) ? timeSlice
				                                                                                                                 : timeSlice - adjustTime;

				try {
					// There would be Exception.
					long actualTurnSleptTime = normalSleep(turnSleepTime, sleepAtLeast);

					// "> 0" means sleep much than expected;
					// "< 0" means sleep less than expected.
					adjustTime = actualTurnSleptTime - turnSleepTime;

					leftTime -= actualTurnSleptTime;
				} catch (Exception e) {
					// Quit sleep anyway.
					break;
				}
			}

			sleptTime = System.currentTimeMillis() - startTime;

			if (log.isTraceEnabled()) {
				log.trace(logId + "Slept time (ms) = " + sleptTime);
			}
		}

		return sleptTime;
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Internally, call sleepWithTimeSlice(?, ?, ?, DEFAULT_MINIMAL_SLEEP_TIME, ?).
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds.
	 * @param timeSlice
	 *        Time slice, milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 * @param sleepAtLeast
	 *        boolean. true = sleep at least specified time; false = not sure sleep less or more.
	 * @return long - Actual slept time, milliseconds.
	 */
	public static long sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime, long timeSlice, boolean sleepAtLeast){
		return sleepWithTimeSlice(requestQuitSignalDto, sleepTime, timeSlice, DEFAULT_MINIMAL_SLEEP_TIME, sleepAtLeast);
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Internally, call sleepWithTimeSlice(?, ?, DEFAULT_TIME_SLICE, DEFAULT_MINIMAL_SLEEP_TIME, ?).
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds.
	 * @param sleepAtLeast
	 *        boolean. true = sleep at least specified time; false = not sure sleep less or more.
	 * @return long - Actual slept time, milliseconds.
	 */
	public static long sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime, boolean sleepAtLeast){
		return sleepWithTimeSlice(requestQuitSignalDto, sleepTime, DEFAULT_TIME_SLICE, DEFAULT_MINIMAL_SLEEP_TIME, sleepAtLeast);
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Use the method, when sleep long time and listen to "request quit" signal.<br>
	 * Internally, call sleepWithTimeSlice(?, ?, ?, ?, true).
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds.
	 * @param timeSlice
	 *        long. Time slice in milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 * @param minimalSleepTime
	 *        long. Minimal sleep time in milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 */
	public static void sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime, long timeSlice, long minimalSleepTime){
		sleepWithTimeSlice(requestQuitSignalDto, sleepTime, timeSlice, minimalSleepTime, true);
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Use the method, when sleep long time and listen to "request quit" signal.<br>
	 * Internally, call sleepWithTimeSlice(?, ?, ?, DEFAULT_MINIMAL_SLEEP_TIME, true).
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds.
	 * @param timeSlice
	 *        Time slice, milliseconds.<br>
	 *        It should better be >= 15 for Windows system.
	 */
	public static void sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime, long timeSlice){
		sleepWithTimeSlice(requestQuitSignalDto, sleepTime, timeSlice, DEFAULT_MINIMAL_SLEEP_TIME, true);
	}

	/**
	 * Sleep with time slice.
	 * <p>
	 * Use the method, when sleep long time and listen to "request quit" signal.<br>
	 * Internally, call sleepWithTimeSlice(?, ?, DEFAULT_TIME_SLICE, DEFAULT_MINIMAL_SLEEP_TIME, true).
	 *
	 * @param requestQuitSignalDto
	 *        RequestQuitSignalDto. Cannot be null.
	 * @param sleepTime
	 *        long. Sleep time in milliseconds.
	 */
	public static void sleepWithTimeSlice(RequestQuitSignalDto requestQuitSignalDto, long sleepTime){
		sleepWithTimeSlice(requestQuitSignalDto, sleepTime, DEFAULT_TIME_SLICE, DEFAULT_MINIMAL_SLEEP_TIME, true);
	}
}
