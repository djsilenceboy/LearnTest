
package com.djs.learn.javalang.basic;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestDateTimeV8
{
	public void testSample1(){
		System.out.println("LocalTime.now() = " + LocalTime.now());
		System.out.println("LocalDate.now() = " + LocalDate.now());
		System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
		System.out.println("ZonedDateTime.now() = " + ZonedDateTime.now());

		System.out.println("----------------------------------------");

		LocalTime time1 = LocalTime.of(6, 15);

		System.out.println(time1);

		LocalDate date1 = LocalDate.of(2016, Month.MAY, 25);
		LocalDate date2 = LocalDate.of(2016, 6, 1);

		System.out.println(date1);
		System.out.println(date2);

		System.out.println("----------------------------------------");

		System.out.println("AvailableZoneIds = " + ZoneId.getAvailableZoneIds());
	}

	public void testSample2(){
		LocalTime tm = LocalTime.now();

		System.out.println("LocalTime.now() = " + tm);

		System.out.println("LocalTime.minusSeconds() = " + tm.minusSeconds(30));
		System.out.println("LocalTime.minusMinutes() = " + tm.minusMinutes(30));
		System.out.println("LocalTime.minusHours() = " + tm.minusHours(5));

		System.out.println("LocalTime.plusSeconds() = " + tm.plusSeconds(30));
		System.out.println("LocalTime.plusMinutes() = " + tm.plusMinutes(30));
		System.out.println("LocalTime.plusHours() = " + tm.plusHours(5));

		System.out.println("----------------------------------------");

		LocalDate dt = LocalDate.now();

		System.out.println("LocalDate.now() = " + dt);

		System.out.println("LocalDate.minusDays() = " + dt.minusDays(2));
		System.out.println("LocalDate.minusWeeks() = " + dt.minusWeeks(2));
		System.out.println("LocalDate.minusMonths() = " + dt.minusMonths(2));

		System.out.println("LocalDate.plusDays() = " + dt.plusDays(2));
		System.out.println("LocalDate.plusWeeks() = " + dt.plusWeeks(2));
		System.out.println("LocalDate.plusMonths() = " + dt.plusMonths(2));

		System.out.println("----------------------------------------");

		LocalDateTime dttm = LocalDateTime.now();

		System.out.println("LocalDateTime.now() = " + dttm);

		System.out.println("LocalDateTime.minusHours() = " + dttm.minusHours(5));
		System.out.println("LocalDateTime.minusDays() = " + dttm.minusDays(1));

		System.out.println("LocalDateTime.plusHours() = " + dttm.plusHours(5));
		System.out.println("LocalDateTime.plusDays() = " + dttm.plusDays(1));
	}

	public void testSample3(){
		LocalDate dt = LocalDate.now();

		System.out.println("LocalDate.now() = " + dt);

		Period p1 = Period.ofDays(5);
		System.out.println("LocalDate.now() + " + p1 + " = " + (dt.plus(p1)));

		Period p2 = Period.ofWeeks(2);
		System.out.println("LocalDate.now() + " + p2 + " = " + (dt.plus(p2)));

		Period p3 = Period.ofMonths(1);
		System.out.println("LocalDate.now() + " + p3 + " = " + (dt.plus(p3)));

		Period p4 = Period.ofYears(1);
		System.out.println("LocalDate.now() + " + p4 + " = " + (dt.plus(p4)));

		System.out.println("----------------------------------------");

		LocalTime tm = LocalTime.now();

		System.out.println("LocalTime.now() = " + tm);

		Duration d1 = Duration.ofDays(1);
		System.out.println("LocalTime.now() + " + d1 + " = " + (tm.plus(d1)));

		Duration d2 = Duration.ofHours(5);
		System.out.println("LocalTime.now() + " + d2 + " = " + (tm.plus(d2)));

		Duration d3 = Duration.ofMinutes(30);
		System.out.println("LocalTime.now() + " + d3 + " = " + (tm.plus(d3)));

		Duration d4 = Duration.ofSeconds(30);
		System.out.println("LocalTime.now() + " + d4 + " = " + (tm.plus(d4)));
	}

	public void testSample4(){
		Instant it1 = Instant.now();
		Instant it2 = Instant.now();

		System.out.println("Instant.now() = " + it1);
		System.out.println("Instant.now() = " + it2);

		Duration d1 = Duration.between(it2, it1);
		System.out.println("Duration = " + d1);
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
	}

}
