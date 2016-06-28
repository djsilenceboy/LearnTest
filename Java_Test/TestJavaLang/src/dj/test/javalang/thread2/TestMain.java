
package dj.test.javalang.thread2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain
{
	public static void main(String[] args){
		TestMain tt = new TestMain();

		System.out.println("************************************************************");
		tt.test1();

		System.out.println("************************************************************");
		tt.test2();

		System.out.println("************************************************************");
		tt.test3();
	}

	public List<SampleThreadB> threadlist = null;

	public void test1(){
		int threadNum = 4;
		SampleThreadA[] threadlist3 = new SampleThreadA[threadNum];
		long timeSlice = 100;
		int i;

		for (i = 0; i < threadNum; i++) {
			threadlist3[i] = new SampleThreadA(5000 + (i * 1000));

			threadlist3[i].start();
		}

		// Wait for all threads to return.
		while (true) {
			boolean quit = true;

			for (i = 0; i < threadNum; i++) {
				if (threadlist3[i] != null) {
					if (threadlist3[i].getState() != Thread.State.TERMINATED) {
						quit = false;
					} else {
						threadlist3[i] = null;

						System.out.println("Detected thread <" + i + "> terminated.");
					}
				}
			}

			if (quit) {
				break;
			}

			try {
				Thread.sleep(timeSlice);
			} catch (Exception e) {

			}
		}

		System.out.println("All threads terminated.");
	}

	public void test2(){
		int threadNum = 4;
		long timeSlice = 100;
		int i;

		threadlist = new ArrayList<SampleThreadB>();

		for (i = 0; i < threadNum; i++) {
			SampleThreadB simpleThread = new SampleThreadB(this, 5000 + (i * 1000));

			threadlist.add(simpleThread);
			simpleThread.start();
		}

		// Wait for all threads to return.
		while (threadlist.size() > 0) {
			try {
				Thread.sleep(timeSlice);
			} catch (Exception e) {

			}
		}

		System.out.println("All threads terminated.");
	}

	public void test3(){
		int threadNum = 4;
		long timeSlice = 100;
		int i;

		threadlist = Collections.synchronizedList(new ArrayList<SampleThreadB>());

		for (i = 0; i < threadNum; i++) {
			SampleThreadB simpleThread = new SampleThreadB(this, 5000 + (i * 1000));

			threadlist.add(simpleThread);
			simpleThread.start();
		}

		// Wait for all threads to return.
		while (threadlist.size() > 0) {
			try {
				Thread.sleep(timeSlice);
			} catch (Exception e) {

			}
		}

		System.out.println("All threads terminated.");
	}
}
