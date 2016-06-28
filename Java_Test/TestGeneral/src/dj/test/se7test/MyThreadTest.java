
package dj.test.se7test;

public class MyThreadTest
{
	public static Perform aP = new Perform("a");
	public static Perform bP = new Perform("b");
	public static Thread a = new Thread(aP);
	public static Thread b = new Thread(bP);

	public static void main(String[] args){
		a.start();
		b.start();
	}
}

class Perform extends Thread
{
	volatile String name;

	Perform(String name){
		this.name = name;
	}

	@Override
	public void run(){
		try {

			if (this.name.equals("a")) {
				System.out.println(this.name + " working");
				MyThreadTest.b.join();
				MyThreadTest.bP.doStuff();
			} else if (this.name.equals("b")) {
				System.out.println(this.name + " working");
				MyThreadTest.a.join();
				MyThreadTest.aP.doStuff();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	synchronized void doStuff() throws Exception{
		if (this.name.equals("a")) {
			System.out.println(this.name);
		} else if (this.name.equals("b")) {
			System.out.println(this.name);
		}
	}
}
