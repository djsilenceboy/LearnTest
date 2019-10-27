
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStreamFindPrime
{
	public boolean isPrime(int candidate){
		int candidateRoot = (int)Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}

	public Map<Boolean, List<Integer>> partitionPrimes1(int n){
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
	}

	public boolean isPrime(List<Integer> primes, int candidate){
		return primes.stream().noneMatch(i -> candidate % i == 0);
	}

	public Map<Boolean, List<Integer>> partitionPrimes2(int n){
		return IntStream.rangeClosed(2, n).boxed().collect(() -> new HashMap<Boolean, List<Integer>>() {
			{
				put(true, new ArrayList<Integer>());
				put(false, new ArrayList<Integer>());
			}
		}, (acc, candidate) -> {
			acc.get(isPrime(acc.get(true), candidate)).add(candidate);
		}, (map1, map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
		});
	}

	private void testPartitionPrimes(Function<Integer, Map<Boolean, List<Integer>>> f, int n){
		long startTime = System.currentTimeMillis();
		Map<Boolean, List<Integer>> results = f.apply(n);
		long stopTime = System.currentTimeMillis();
		System.out.println("PartitionPrimes = " + results);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		TestStreamFindPrime app = new TestStreamFindPrime();

		app.testPartitionPrimes(app::partitionPrimes1, 20);
		app.testPartitionPrimes(app::partitionPrimes2, 20);
	}
}
