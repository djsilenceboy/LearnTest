
package dj.test.se7test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest
{
	public static BlockingQueue<Integer> bq;

	BlockingQueueTest(ArrayBlockingQueue<Integer> b){
		bq = b;
	}

	public static void main(String[] args){
		new BlockingQueueTest(new ArrayBlockingQueue(5));
		(new X()).start();
		(new Y()).start();
	}

	public static void put(Thread t){
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Inserting " + i);
				bq.put(i);

				if (i == 4) t.sleep(4000);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted: " + e.getMessage());
		}
	}

	public static void take(){
		try {
			for (int i = 0; i < 10; i++)
				System.out.println("taking " + bq.take());
		} catch (InterruptedException e) {
			System.out.println("Interrupted: " + e.getMessage());
		}
	}
}

class X extends Thread
{
	@Override
	public void run(){
		BlockingQueueTest.put(this);
	}
}

class Y extends Thread
{
	@Override
	public void run(){
		BlockingQueueTest.take();
	}
}
