
package dj.test.javalang.collections;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestMainPrimitveStreamV8
{
	public void testSample1(){
		{
			IntStream is = IntStream.of(1, 2, 3, Integer.MIN_VALUE);

			is.forEach(item -> System.out.println(item));
		}

		System.out.println("--------------------");

		{
			LongStream ls = LongStream.of(1, 2, 3, Long.MIN_VALUE);

			ls.forEach(item -> System.out.println(item));
		}

		System.out.println("--------------------");

		{
			DoubleStream ds = DoubleStream.of(1, 2, 3, Double.NEGATIVE_INFINITY, Double.NaN);

			ds.forEach(item -> System.out.println(item));
		}
	}

	public void testRange(){
		{
			IntStream is = IntStream.range(1, 5);

			is.forEach(item -> System.out.print(item));

			System.out.println();
		}

		System.out.println("--------------------");

		{
			IntStream is = IntStream.rangeClosed(1, 5);

			is.forEach(item -> System.out.print(item));

			System.out.println();
		}
	}

	public void testOptional(){
		{
			IntStream is = IntStream.range(1, 11);

			OptionalDouble avg = is.average();

			System.out.println("Average = " + avg);
			System.out.println("Average = " + avg.getAsDouble());
		}

		System.out.println("--------------------");

		{
			IntStream is = IntStream.range(1, 11);

			OptionalInt max = is.max();

			System.out.println("Max = " + max);
			System.out.println("Max = " + max.getAsInt());
		}
	}

	public void testStatistic(){
		{
			IntStream is = IntStream.range(1, 11);

			IntSummaryStatistics iss = is.summaryStatistics();

			System.out.println("IntSummaryStatistics = " + iss);
		}

		System.out.println("--------------------");

		{
			DoubleStream is = DoubleStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

			DoubleSummaryStatistics iss = is.summaryStatistics();

			System.out.println("DoubleSummaryStatistics = " + iss);
		}
	}

	public void testStreamTo(){
		{
			Stream<Integer> s = Stream.of(1, 2, 3);
			IntStream is = s.mapToInt(x -> x * 2);
			// Stream cannot be reused. There will be exception.
			DoubleStream ds = s.mapToDouble(x -> x);
			// If not add "(int)", there will be compiling error.
			IntStream is2 = ds.mapToInt(x -> (int)x);

			ds.forEach(System.out::println);
		}
	}

	public static void main(String[] args){
		TestMainPrimitveStreamV8 testMain = new TestMainPrimitveStreamV8();

		testMain.testSample1();
		System.out.println("============================================================");

		testMain.testRange();
		System.out.println("============================================================");

		testMain.testOptional();
		System.out.println("============================================================");

		testMain.testStatistic();
		System.out.println("============================================================");

		testMain.testStreamTo();
		System.out.println("============================================================");
	}

}
