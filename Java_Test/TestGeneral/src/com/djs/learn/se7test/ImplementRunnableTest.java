
package com.djs.learn.se7test;

public class ImplementRunnableTest
{
	public static void main(String[] args){
		try {
			new Thread(new Action()).start();
			Thread.sleep(100);
			new Thread(new Action()).start();
			Thread.sleep(100);
			new Thread(new Action()).start();
		} catch (InterruptedException e) {
			System.out.println("Thread Interrupted");
		}
	}
}

class Action implements Runnable
{
	static int count = 0;
	static String[] letters = {"A", "B", "C", "D"};

	public void run(){
		try {
			System.out.println("Count = " + count);
			System.out.println(letters[count]);

			if (count == 2) {
				throw new Exception();
			}

			count++;
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
