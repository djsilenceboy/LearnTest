
package com.djs.learn.javalang.basic;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class TestDateTimeV8
{
	public void testSample1(){
		System.out.println("LocalDate.now()     = " + LocalDate.now());
		System.out.println("LocalTime.now()     = " + LocalTime.now());
		System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
		System.out.println("Instant.now()       = " + Instant.now());
		System.out.println("ZonedDateTime.now() = " + ZonedDateTime.now());

		System.out.println("----------------------------------------");

		LocalTime time1 = LocalTime.of(6, 15);

		System.out.println(time1);

		LocalDate date1 = LocalDate.of(2016, Month.MAY, 25);
		LocalDate date2 = LocalDate.of(2016, 6, 1);

		System.out.println(date1);
		System.out.println(date2);

		LocalDateTime datetime1 = LocalDateTime.of(date1, time1);
		LocalDateTime datetime2 = LocalDateTime.of(2010, 1, 8, 12, 30);

		System.out.println(datetime1);
		System.out.println(datetime2);
	}

	public void testSample2(){
		LocalTime time = LocalTime.now();

		System.out.println("LocalTime.now() = " + time);

		System.out.println("LocalTime.minusSeconds() = " + time.minusSeconds(30));
		System.out.println("LocalTime.minusMinutes() = " + time.minusMinutes(30));
		System.out.println("LocalTime.minusHours() = " + time.minusHours(5));

		System.out.println("LocalTime.plusSeconds() = " + time.plusSeconds(30));
		System.out.println("LocalTime.plusMinutes() = " + time.plusMinutes(30));
		System.out.println("LocalTime.plusHours() = " + time.plusHours(5));

		System.out.println("----------------------------------------");

		LocalDate date = LocalDate.now();

		System.out.println("LocalDate.now() = " + date);

		System.out.println("LocalDate.minusDays() = " + date.minusDays(2));
		System.out.println("LocalDate.minusWeeks() = " + date.minusWeeks(2));
		System.out.println("LocalDate.minusMonths() = " + date.minusMonths(2));

		System.out.println("LocalDate.plusDays() = " + date.plusDays(2));
		System.out.println("LocalDate.plusWeeks() = " + date.plusWeeks(2));
		System.out.println("LocalDate.plusMonths() = " + date.plusMonths(2));

		System.out.println("----------------------------------------");

		LocalDateTime datetime = LocalDateTime.now();

		System.out.println("LocalDateTime.now() = " + datetime);

		System.out.println("LocalDateTime.minusHours() = " + datetime.minusHours(5));
		System.out.println("LocalDateTime.minusDays() = " + datetime.minusDays(1));

		System.out.println("LocalDateTime.plusHours() = " + datetime.plusHours(5));
		System.out.println("LocalDateTime.plusDays() = " + datetime.plusDays(1));
	}

	public void testSample3(){
		LocalDate date = LocalDate.now();

		System.out.println("LocalDate.now() = " + date);

		Period p1 = Period.ofDays(5);
		System.out.println("LocalDate.now() + " + p1 + " = " + (date.plus(p1)));

		Period p2 = Period.ofWeeks(2);
		System.out.println("LocalDate.now() + " + p2 + " = " + (date.plus(p2)));

		Period p3 = Period.ofMonths(1);
		System.out.println("LocalDate.now() + " + p3 + " = " + (date.plus(p3)));

		Period p4 = Period.ofYears(1);
		System.out.println("LocalDate.now() + " + p4 + " = " + (date.plus(p4)));

		System.out.println("----------------------------------------");

		LocalTime time = LocalTime.now();

		System.out.println("LocalTime.now() = " + time);

		Duration d1 = Duration.ofDays(1);
		System.out.println("LocalTime.now() + " + d1 + " = " + (time.plus(d1)));

		Duration d2 = Duration.ofHours(5);
		System.out.println("LocalTime.now() + " + d2 + " = " + (time.plus(d2)));

		Duration d3 = Duration.ofMinutes(30);
		System.out.println("LocalTime.now() + " + d3 + " = " + (time.plus(d3)));

		Duration d4 = Duration.ofSeconds(30);
		System.out.println("LocalTime.now() + " + d4 + " = " + (time.plus(d4)));
	}

	public void testSample4(){
		Instant it1 = Instant.now();
		Instant it2 = Instant.now().plusSeconds(11);

		System.out.println("Instant1 = " + it1);
		System.out.println("Instant2 = " + it2);

		System.out.println("----------------------------------------");

		Duration d1 = Duration.between(it1, it2);
		System.out.println("Duration (1 - 2) = " + d1);

		Duration d2 = Duration.between(it2, it1);
		System.out.println("Duration (2 - 1) = " + d2);

		System.out.println("----------------------------------------");

		System.out.println("Instant1.getEpochSecond = " + it1.getEpochSecond());
		System.out.println("Instant1.toEpochMilli = " + it1.toEpochMilli());
	}

	public void testSample5(){
		System.out.println("ChronoUnit \t DateBased \t TimeBased \t Duration");
		System.out.println("----------------------------------------");

		for (ChronoUnit unit : ChronoUnit.values()) {
			System.out.printf("%10s \t %b \t\t %b \t\t %s %n", unit, unit.isDateBased(), unit.isTimeBased(), unit.getDuration());
		}

		System.out.println("----------------------------------------");

		System.out.println("Duration.(ChronoUnit.SECONDS) = " + Duration.of(8, ChronoUnit.SECONDS));
		System.out.println("Duration.(ChronoUnit.DAYS) = " + Duration.of(8, ChronoUnit.DAYS));
	}

	public void testSample6(){
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println("ZoneId.AvailableZoneIds = " + availableZoneIds.size());
		System.out.println("ZoneId.getAvailableZoneIds() = " + availableZoneIds);
		availableZoneIds.stream().limit(10).forEach(zoneIdName -> System.out.println(" -> " + zoneIdName));
		System.out.println(" -> ......");

		System.out.println("----------------------------------------");

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime datetime = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();

		System.out.println("LocalDate.now()        = " + date);
		System.out.println("LocalTime.now()        = " + time);
		System.out.println("LocalDateTime.now() = " + datetime);
		System.out.println("ZoneId.systemDefault() = " + zoneId);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zoneId);
		System.out.println("ZonedDateTime(Default) = " + zonedDateTime);
		zonedDateTime = datetime.atZone(zoneId);
		System.out.println("ZonedDateTime(Default) = " + zonedDateTime);

		System.out.println("----------------------------------------");

		ZoneId zoneId1 = ZoneId.of("Japan");
		ZonedDateTime zonedDateTime1 = ZonedDateTime.of(date, time, zoneId1);
		System.out.println("ZonedDateTime(Japan)   = " + zonedDateTime1);

		ZoneId zoneId2 = ZoneId.of("Australia/Sydney");
		ZonedDateTime zonedDateTime2 = ZonedDateTime.of(date, time, zoneId2);
		System.out.println("ZonedDateTime(Sydney)  = " + zonedDateTime2);

		ZoneOffset zoneOffset1 = zonedDateTime1.getOffset();
		ZoneOffset zoneOffset2 = zonedDateTime2.getOffset();

		System.out.println("ZoneOffset(Japan)      = " + zoneOffset1);
		System.out.println("ZoneOffset(Sydney)     = " + zoneOffset2);
	}

	public void testSample7(){
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime datetime = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zoneId);

		System.out.println("BASIC_ISO_DATE      = " + DateTimeFormatter.BASIC_ISO_DATE.format(date));
		System.out.println("ISO_DATE            = " + DateTimeFormatter.ISO_DATE.format(date));
		System.out.println("ISO_TIME            = " + DateTimeFormatter.ISO_TIME.format(time));
		System.out.println("ISO_DATE_TIME       = " + DateTimeFormatter.ISO_DATE_TIME.format(datetime));
		System.out.println("ISO_ZONED_DATE_TIME = " + DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime));
		System.out.println("RFC_1123_DATE_TIME  = " + DateTimeFormatter.RFC_1123_DATE_TIME.format(zonedDateTime));

		System.out.println("----------------------------------------");

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS z");
		System.out.println("Pattern = " + dateTimeFormatter.format(zonedDateTime));

		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
		System.out.println("Pattern = " + dateTimeFormatter.format(datetime));

		dateTimeFormatter = DateTimeFormatter.ofPattern("E, EEEE, w, W, D, d, G, a, H, h, K, k");
		System.out.println("Pattern = " + dateTimeFormatter.format(datetime));
	}

	public static void main(String[] args){
		TestDateTimeV8 test = new TestDateTimeV8();

		test.testSample1();
		System.out.println("============================================================");

		test.testSample2();
		System.out.println("============================================================");

		test.testSample3();
		System.out.println("============================================================");

		test.testSample4();
		System.out.println("============================================================");

		test.testSample5();
		System.out.println("============================================================");

		test.testSample6();
		System.out.println("============================================================");

		test.testSample7();
		System.out.println("============================================================");
	}

}
