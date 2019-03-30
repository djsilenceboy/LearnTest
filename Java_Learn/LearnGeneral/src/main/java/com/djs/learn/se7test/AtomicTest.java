
package com.djs.learn.se7test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest
{
	public static void main(String[] args){
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread extends Thread
{
	static AtomicInteger d = new AtomicInteger(9);

	@Override
	synchronized public void run(){
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			System.out.println("Error");
		}

		System.out.println(d.decrementAndGet());
	}

	public void decrement(){
		d.decrementAndGet();
	}
}
