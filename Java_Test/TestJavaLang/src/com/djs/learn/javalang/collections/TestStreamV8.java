
package com.djs.learn.javalang.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamV8
{
	public void testCreate(){
		Stream<String> empty = Stream.empty();
		Stream<Integer> singleElement = Stream.of(1);
		Stream<Integer> fromArray = Stream.of(1, 2, 3);

		System.out.println("empty = " + empty);
		System.out.println("singleElement = " + singleElement);
		System.out.println("fromArray = " + fromArray);
	}

	public void testTerminalOperations(){
		// Cannot reuse stream, as these methods are terminal operations.
		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.count() = " + s.count());
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
			min.ifPresent(System.out::println);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			Optional<String> max = s.max((s1, s2) -> s1.length() - s2.length());
			max.ifPresent(System.out::println);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			Optional<String> findAny = s.findAny();
			findAny.ifPresent(System.out::println);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			Optional<String> findFirst = s.findFirst();
			findFirst.ifPresent(System.out::println);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			Optional<String> findFirst = s.findFirst();
			findFirst.ifPresent(System.out::println);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.allMatch() = " + s.allMatch(x -> x.length() == 6));
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.anyMatch() = " + s.anyMatch(x -> x.length() == 6));
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.noneMatch() = " + s.noneMatch(x -> x.length() == 6));
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			s.forEach(System.out::print);
			System.out.println();
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			String result = s.reduce("Start", (s1, s2) -> s1 = s1 + "-" + s2);
			System.out.println("Stream.reduce() = " + result);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			String result = s.reduce("Line", String::concat);
			System.out.println("Stream.reduce() = " + result);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			TreeSet<String> collect = s.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
			System.out.println("Stream.collect() = " + collect);
		}

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			StringBuilder collect = s.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
			System.out.println("Stream.collect() = " + collect);
		}
	}

	public void testIntermediateOperations(){
		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.filter(x -> x.length() > 6).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.distinct().forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.skip(2).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.limit(2).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			// Map is similar to covert something.
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.map(x -> x.length()).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			{
				Stream<List<String>> s = Stream.of(Arrays.asList("monkey", "gorilla"), Arrays.asList("bonobo"), Arrays.asList("monkey", "gorilla"));
				s.forEach(System.out::println);
			}

			{
				Stream<List<String>> s = Stream.of(Arrays.asList("monkey", "gorilla"), Arrays.asList("bonobo"), Arrays.asList("monkey", "gorilla"));
				s.flatMap(x -> x.stream()).forEach(System.out::println);
			}
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.sorted().forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.sorted(Comparator.reverseOrder()).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			s.peek(x -> System.out.print(x + "-")).forEach(x -> System.out.println(x.length()));
		}
	}

	public void testGenerate(){
		{
			Stream<Double> s = Stream.generate(Math::random);
			s.limit(5).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<Double> odd = Stream.iterate(1.0, x -> x / 2);
			odd.limit(5).forEach(System.out::println);
		}

		System.out.println("--------------------");

		{
			Stream<Integer> odd = Stream.iterate(1, x -> x + 2);
			odd.limit(5).forEach(System.out::println);
		}
	}

	public void testCollectors1(){
		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.collect(Collectors.joining()) = " + s.collect(Collectors.joining()));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.collect(Collectors.joining()) = " + s.collect(Collectors.joining(",")));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.toCollection()) = " + s.collect(Collectors.toCollection(TreeSet::new)));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
			System.out.println("Stream.collect(Collectors.toMap()) = " + s.collect(Collectors.toMap(x -> x, String::length)));
		}
	}

	public void testCollectors2(){
		{
			Stream<String> s = Stream.empty();
			System.out.println("Stream.collect(Collectors.groupingBy(Map/List)) = " + s.collect(Collectors.groupingBy(String::length)));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.groupingBy(Map/List)) = " + s.collect(Collectors.groupingBy(String::length)));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.groupingBy(Map/Set)) = " + s.collect(Collectors.groupingBy(String::length, Collectors.toSet())));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.groupingBy(TreeMap/List)) = "
			        + s.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList())));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.groupingBy(TreeMap/Set)) = "
			        + s.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet())));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.empty();
			System.out.println("Stream.collect(Collectors.partitioningBy(List)) = " + s.collect(Collectors.partitioningBy(x -> x.length() > 6)));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.partitioningBy(List)) = " + s.collect(Collectors.partitioningBy(x -> x.length() > 6)));
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of("monkey", "gorilla", "bonobo", "monkey", "gorilla");
			System.out.println("Stream.collect(Collectors.partitioningBy(Set)) = "
			        + s.collect(Collectors.partitioningBy(x -> x.length() > 6, Collectors.toSet())));
		}
	}

	public void testOptional(){
		{
			Stream<String> s = Stream.of("monkey");
			Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
			System.out.println(min);
		}

		System.out.println("--------------------");

		{
			Stream<String> s = Stream.of();
			Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
			System.out.println(min);
			// Optional.empty
		}

		System.out.println("--------------------");

		{
			Optional<String> min = Optional.empty();
			System.out.println(min);
			// Optional.empty
		}

		System.out.println("--------------------");

		{
			Optional<String> min = Optional.ofNullable(null);
			System.out.println(min);
			// Optional.empty
		}

		System.out.println("--------------------");

		{
			try {
				Optional<String> min = Optional.of(null);
				System.out.println(min);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void testUnorder(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		list.stream().unordered().parallel().forEach(item -> System.out.println(item));
	}

	public void testReduce(){
		{
			List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
			System.out.println("Reduce = " + list.stream().reduce("", (c, s1) -> c + s1));
		}

		System.out.println("--------------------");

		{
			List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
			System.out.println("Reduce = " + list.stream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));
		}

		System.out.println("--------------------");

		{
			List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
			System.out.println("Reduce = " + list.parallelStream().reduce("", (c, s1) -> c + s1));
		}

		System.out.println("--------------------");

		{
			List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
			System.out.println("Reduce = " + list.parallelStream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));
		}
	}

	public void testParallel(){
		{
			List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			list.parallelStream().forEach(item -> System.out.println(item));
		}

		System.out.println("--------------------");

		{
			List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			System.out.println("FindAny = " + list.parallelStream().findAny().get());
		}
	}

	public void testSample1(){
		List<Integer> list = Arrays.asList(8, 18, 28, 38);

		list.stream().filter(item -> item > 20).forEach(item -> System.out.println(item));
		System.out.println("--------------------");
		list.parallelStream().filter(item -> item < 20).forEach(item -> System.out.println(item));
	}

	public void monitorFlow(){
		{
			List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			list.stream().peek(item -> System.out.print("<" + item + "> ")).map(i -> i * i).forEach(item -> System.out.println(item));
		}

		System.out.println("--------------------");

		{
			// If no terminal, no data output.
			List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			list.stream().peek(item -> System.out.print("<" + item + "> ")).map(i -> i * i);
		}

		System.out.println("--------------------");

		{
			// Parallel, not in order.
			List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			list.parallelStream().peek(item -> System.out.print("<" + item + "> ")).map(i -> i * i).forEach(item -> System.out.println(item));
		}
	}

	public static void main(String[] args){
		TestStreamV8 testMain = new TestStreamV8();

		testMain.testCreate();
		System.out.println("============================================================");

		testMain.testTerminalOperations();
		System.out.println("============================================================");

		testMain.testIntermediateOperations();
		System.out.println("============================================================");

		testMain.testGenerate();
		System.out.println("============================================================");

		testMain.testCollectors1();
		System.out.println("============================================================");

		testMain.testCollectors2();
		System.out.println("============================================================");

		testMain.testOptional();
		System.out.println("============================================================");

		testMain.testUnorder();
		System.out.println("============================================================");

		testMain.testReduce();
		System.out.println("============================================================");

		testMain.testParallel();
		System.out.println("============================================================");

		testMain.testSample1();
		System.out.println("============================================================");

		testMain.monitorFlow();
		System.out.println("============================================================");
	}
}
