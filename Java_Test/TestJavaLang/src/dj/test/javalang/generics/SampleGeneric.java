
package dj.test.javalang.generics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleGeneric
{
	public class LevelA
	{}

	public class LevelB extends LevelA
	{}

	public class LevelC extends LevelB
	{}

	public class LevelD extends LevelC
	{}

	public class LevelE extends LevelD
	{}

	public class SampleTypeC<T extends LevelB>
	{}

	public void printA(SampleTypeC<? super LevelB> t){
		System.out.println("A: T <" + t.getClass().getName() + "> = " + t);
	}

	public void printB(SampleTypeC<? super LevelD> t){
		System.out.println("B: T <" + t.getClass().getName() + "> = " + t);
	}

	public void printC(SampleTypeC<? extends LevelD> t){
		System.out.println("C: T <" + t.getClass().getName() + "> = " + t);
	}

	public <T> void printT(T t){
		System.out.println("T <" + t.getClass().getName() + "> = " + t);
	}

	public void printList(List<?> list){
		// Unbounded type is immutable.
		// list.add(new Object()); // Not compile.
		for (Object x : list)
			System.out.println("List ? <" + x.getClass().getName() + "> = " + x);
	}

	public void printList2(List<? extends IOException> list){
		// Upper-Bounded type is immutable.
		// list.add(new IOException("Problem 3")); // Not compile.
		for (Object x : list)
			System.out.println("List extends <" + x.getClass().getName() + "> = " + x);
	}

	public void printList3(List<? super IOException> list){
		// Lower-bounded type is mutable.
		list.add(new IOException("Problem 3"));
		for (Object x : list)
			System.out.println("List super <" + x.getClass().getName() + "> = " + x);
	}

	public void testSample1(){
		SampleTypeA<Integer> sampleTypeA = new SampleTypeA<Integer>(10);

		sampleTypeA.print();
		new SampleTypeA(20).print();
		new SampleTypeA(30.0).print();
		new SampleTypeA(40.0f).print();
		new SampleTypeA("Hello").print();

		System.out.println("----------------------------------------");

		// Only LevelB.
		printA(new SampleTypeC<LevelB>());

		// From LevelD up to LevelB.
		printB(new SampleTypeC<LevelB>());
		printB(new SampleTypeC<LevelC>());
		printB(new SampleTypeC<LevelD>());

		// From LevelD down to LevelE.
		printC(new SampleTypeC<LevelD>());
		printC(new SampleTypeC<LevelE>());

		System.out.println("----------------------------------------");

		{
			new SampleTypeB<Integer>(10, 10);
		}

		System.out.println("----------------------------------------");

		{
			new SampleTypeB<Integer>(10, "Ok");
		}
	}

	public void testSample2(){
		printT(new Integer(10));

		System.out.println("----------------------------------------");

		ArrayList<String> list = new ArrayList<String>();

		list.add("Tom");
		list.add("Jerry");
		list.add("Mary");

		printList(list);

		System.out.println("----------------------------------------");

		List<?> list1a = new ArrayList<IOException>();
		List<?> list1b = new ArrayList<FileNotFoundException>();

		// Upper-Bounded type is immutable.
		// list1a.add(new IOException("Problem 1")); // Not compile.
		// list1a.add(new FileNotFoundException("Problem 2")); // Not compile.
		// list1b.add(new IOException("Problem 1")); // Not compile.
		// list1b.add(new FileNotFoundException("Problem 2")); // Not compile.

		printList(list1a);
		printList(list1b);
		// printList2(list1a); // Not compile.
		// printList3(list1a); // Not compile.

		System.out.println("----------------------------------------");

		List<? extends IOException> list2a = new ArrayList<IOException>();
		List<? extends IOException> list2b = new ArrayList<FileNotFoundException>();

		// Upper-Bounded type is immutable.
		// list2a.add(new IOException("Problem 1")); // Not compile.
		// list2a.add(new FileNotFoundException("Problem 2")); // Not compile.
		// list2b.add(new IOException("Problem 1")); // Not compile.
		// list2b.add(new FileNotFoundException("Problem 2")); // Not compile.

		printList(list2a);
		printList(list2b);
		printList2(list2a);
		printList2(list2b);
		// printList3(list2a); // Not compile.

		System.out.println("----------------------------------------");

		List<? super IOException> list3a = new ArrayList<Exception>();
		List<? super IOException> list3b = new ArrayList<IOException>();
		List<? super IOException> list3c = new ArrayList<Object>();

		// Lower-bounded type is mutable.
		// IOException and all its subclass can be added!

		// list3a.add(new Exception("Problem 0")); // Not compile.
		// list3a.add(new Object()); // Not compile.
		list3a.add(new IOException("Problem 1a"));
		list3b.add(new IOException("Problem 2a"));
		list3c.add(new IOException("Problem 3a"));
		// FileNotFoundException is subclass of IOException.
		list3a.add(new FileNotFoundException("Problem 1b"));
		list3b.add(new FileNotFoundException("Problem 2b"));
		list3c.add(new FileNotFoundException("Problem 3b"));

		printList(list3a);
		printList(list3b);
		printList(list3c);
		// printList2(list3a); // Not compile.
		printList3(list3a);
		printList3(list3b);
		printList3(list3c);
	}

	public static void main(String[] args){
		SampleGeneric testMain = new SampleGeneric();

		System.out.println("============================================================");

		testMain.testSample1();

		System.out.println("============================================================");

		testMain.testSample2();

		System.out.println("============================================================");
	}
}

class SampleTypeA<T>
{
	T t;

	public SampleTypeA(T t){
		this.t = t;
	}

	public void print(){
		System.out.println("T <" + t.getClass().getName() + "> = " + t);
	}

	@Override
	public String toString(){
		return "[t=" + t + "]";
	}
}

class SampleTypeB<T>
{
	<X> SampleTypeB(T t, X x){
		boolean same = x.equals(t);

		System.out.println("T <" + t.getClass().getName() + "> = " + t);
		System.out.println("X <" + x.getClass().getName() + "> = " + x);
		System.out.println("t eguals x = " + same);
	}
}
