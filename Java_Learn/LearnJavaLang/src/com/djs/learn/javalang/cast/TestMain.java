
package com.djs.learn.javalang.cast;

public class TestMain
{
	public void testCast(){
		ChildA childA1 = new ChildA();
		childA1.print();

		System.out.println("************************************************************");

		ChildB childB1 = new ChildB();
		childB1.print();

		System.out.println("************************************************************");

		System.out.println("Down cast.");

		Parent parent1 = childA1;
		parent1.print();

		parent1 = childB1;
		parent1.print();

		System.out.println("************************************************************");

		System.out.println("Up cast wrongly after down cast.");

		try {
			ChildA childA2 = (ChildA)parent1;
			childA2.print();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("************************************************************");

		System.out.println("Up cast correctly after down cast.");

		ChildB childB2 = (ChildB)parent1;
		childB2.print();

		System.out.println("************************************************************");

		System.out.println("Up cast wrongly.");

		try {
			Parent parent2 = new Parent();
			ChildA childA3 = (ChildA)parent2;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args){
		TestMain testMain = new TestMain();

		testMain.testCast();
	}
}
