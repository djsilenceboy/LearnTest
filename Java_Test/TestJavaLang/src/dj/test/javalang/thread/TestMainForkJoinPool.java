
package dj.test.javalang.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class TestMainForkJoinPool
{
	public static void main(String[] args){
		ForkJoinPool threadPool = new ForkJoinPool();

		System.out.println("============================================================");

		threadPool.invoke(new SampleAction(1, 0, 10));

		System.out.println("============================================================");

		SampleTask task = new SampleTask(1, 0, 10);
		threadPool.invoke(task);
		System.out.println("SampleTask = " + task.join());

		System.out.println("============================================================");

		int n = 10;
		Fibonacci fibonacci = new Fibonacci(n);
		threadPool.invoke(fibonacci);
		System.out.println("Fibonacci (" + n + ") = " + fibonacci.join());

		System.out.println("============================================================");
	}
}

class SampleAction extends RecursiveAction
{
	int level;
	int from;
	int to;

	public SampleAction(int level, int from, int to){
		this.level = level;
		this.from = from;
		this.to = to;
	}

	@Override
	protected void compute(){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		if ((to - from) <= 1) {
			System.out.printf(logId + "%-5s <%2s - %2s>\n", level, from, to);
		} else {
			invokeAll(new SampleAction(level * 10, from, (from + to) / 2), new SampleAction(level * 10 + 1, (from + to) / 2 + 1, to));
			System.out.printf(logId + "%-5s <%2s - %2s>\n", level, from, to);
		}
	}
}

class SampleTask extends RecursiveTask<String>
{
	int level;
	int from;
	int to;

	public SampleTask(int level, int from, int to){
		this.level = level;
		this.from = from;
		this.to = to;
	}

	@Override
	protected String compute(){
		String logId = "(" + Thread.currentThread().getName() + ") ";

		if ((to - from) <= 1) {
			return String.format(logId + "%-5s <%2s - %2s>", level, from, to);
		} else {
			SampleTask t1 = new SampleTask(level * 10, from, (from + to) / 2);
			SampleTask t2 = new SampleTask(level * 10 + 1, (from + to) / 2 + 1, to);

			t2.fork();

			return String.format(logId + "%-5s [%s %s]", level, t1.compute(), t2.join());
		}
	}
}

class Fibonacci extends RecursiveTask<Integer>
{
	final int n;

	Fibonacci(int n){
		this.n = n;
	}

	@Override
	protected Integer compute(){
		if (n <= 1) return n;

		Fibonacci f1 = new Fibonacci(n - 1);
		Fibonacci f2 = new Fibonacci(n - 2);

		f2.fork();

		return f1.compute() + f2.join();
	}
}
