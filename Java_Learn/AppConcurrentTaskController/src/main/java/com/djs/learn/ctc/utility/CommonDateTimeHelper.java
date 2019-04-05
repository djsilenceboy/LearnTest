
package com.djs.learn.ctc.utility;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Common date/time helper.
 * <p>
 * Making, converting, calculating for same/different date/time formats.
 */
public class CommonDateTimeHelper
{
	/**
	 * Get UTC time (in milliseconds).
	 *
	 * @return long - Current UTC time in milliseconds.
	 */
	public static long getUtcTime(){
		return System.currentTimeMillis();
	}

	/**
	 * Get UTC time (in milliseconds).
	 * <p>
	 * The valid ranges for input parameters are not checked.
	 *
	 * @param year
	 *        int, since 1970.
	 * @param month
	 *        int, [0 (Jan),11 (Dec)].
	 * @param date
	 *        int, [1,28-31].
	 * @param hourOfDay
	 *        int, [0,23].
	 * @param minute
	 *        int, [0,59].
	 * @param second
	 *        int, [0,59].
	 * @param timeZone
	 *        String, format like "GMT+8", "GMT+8:00". Can be null, to use default time zone.
	 * @return long - Specified UTC time in milliseconds.
	 */
	public static long getUtcTime(int year, int month, int date, int hourOfDay, int minute, int second, String timeZone){
		Calendar calendar = convertUtcTimeToCalendar(year, month, date, hourOfDay, minute, second, timeZone);

		return calendar.getTimeInMillis();
	}

	/**
	 * Convert UTC time to Calendar.
	 * <p>
	 * The valid ranges for input parameters are not checked.
	 *
	 * @param time
	 *        long, UTC time in milliseconds.
	 * @param timeZone
	 *        String, format like "GMT+8", "GMT+8:00". Can be null, then use default time zone.
	 * @return Calendar
	 */
	public static Calendar convertUtcTimeToCalendar(long time, String timeZone){
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(time);

		if (timeZone != null) {
			calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		}

		return calendar;
	}

	/**
	 * Convert UTC time to Calendar.
	 * <p>
	 * The valid ranges for input parameters are not checked.
	 *
	 * @param time
	 *        long, UTC time in milliseconds.
	 * @return Calendar
	 */
	public static Calendar convertUtcTimeToCalendar(long time){
		return convertUtcTimeToCalendar(time, null);
	}

	/**
	 * Convert UTC time to Calendar.
	 * <p>
	 * The valid ranges for input parameters are not checked.
	 *
	 * @param year
	 *        int, since 1970.
	 * @param month
	 *        int, [0 (Jan),11 (Dec)].
	 * @param date
	 *        int, [1,28-31].
	 * @param hourOfDay
	 *        int, [0,23].
	 * @param minute
	 *        int, [0,59].
	 * @param second
	 *        int, [0,59].
	 * @param timeZone
	 *        String, format like "GMT+8", "GMT+8:00". Can be null, to use default time zone.
	 * @return Calendar
	 */
	public static Calendar convertUtcTimeToCalendar(int year, int month, int date, int hourOfDay, int minute, int second, String timeZone){
		Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, date, hourOfDay, minute, second);

		if (timeZone != null) {
			calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		}

		return calendar;
	}

	/**
	 * Add seconds to UTC time.
	 *
	 * @param originalTime
	 *        UTC time in milliseconds.
	 * @param second
	 *        If < 0 means minus. The value can > |60|.
	 * @return long - New UTC time in milliseconds.
	 */
	public static long addSeconds(long originalTime, int second){
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(originalTime);
		calendar.add(Calendar.SECOND, second);

		return calendar.getTimeInMillis();
	}

	/**
	 * Add seconds to Calendar.
	 *
	 * @param originalTime
	 *        Original time in calendar.
	 * @param second
	 *        If < 0 means minus. The value can > |60|.
	 * @return Calendar - New time in Calendar.
	 */
	public static Calendar addSeconds(Calendar originalTime, int second){
		originalTime.add(Calendar.SECOND, second);

		return originalTime;
	}

	/**
	 * Add minutes to UTC time.
	 *
	 * @param originalTime
	 *        UTC time in milliseconds.
	 * @param minute
	 *        If < 0 means minus. The value can > |60|.
	 * @return long - New UTC time in milliseconds.
	 */
	public static long addMinutes(long originalTime, int minute){
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(originalTime);
		calendar.add(Calendar.MINUTE, minute);

		return calendar.getTimeInMillis();
	}

	/**
	 * Add minutes to Calendar.
	 *
	 * @param originalTime
	 *        Original time in calendar.
	 * @param minute
	 *        If < 0 means minus. The value can > |60|.
	 * @return Calendar - New time in Calendar.
	 */
	public static Calendar addMinutes(Calendar originalTime, int minute){
		originalTime.add(Calendar.MINUTE, minute);

		return originalTime;
	}

	/**
	 * Add hours to UTC time.
	 *
	 * @param originalTime
	 *        UTC time in milliseconds.
	 * @param hour
	 *        If < 0 means minus. The value can > |24|.
	 * @return long - New UTC time in milliseconds.
	 */
	public static long addHours(long originalTime, int hour){
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(originalTime);
		calendar.add(Calendar.HOUR_OF_DAY, hour);

		return calendar.getTimeInMillis();
	}

	/**
	 * Add hours to Calendar.
	 *
	 * @param originalTime
	 *        Original time in calendar.
	 * @param hour
	 *        If < 0 means minus. The value can > |24|.
	 * @return Calendar - New time in Calendar.
	 */
	public static Calendar addHours(Calendar originalTime, int hour){
		originalTime.add(Calendar.HOUR_OF_DAY, hour);

		return originalTime;
	}
}
