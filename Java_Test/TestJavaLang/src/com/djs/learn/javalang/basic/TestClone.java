
package com.djs.learn.javalang.basic;

import java.util.LinkedList;

public class TestClone
{
	public void testA(){
		try {
			SampleCloneA sample1 = new SampleCloneA();
			sample1.student.add("Tom");
			SampleCloneA sample2 = (SampleCloneA)sample1.clone();
			sample2.school = "Mouse school";
			sample2.student.add("Jerry");

			System.out.println("--------------------");

			System.out.println("sample1 = " + sample1);
			System.out.println("sample2 = " + sample2);
		} catch (Exception e) {

		}
	}

	public void testB(){
		try {
			SampleCloneB sample1 = new SampleCloneB();
			sample1.student.add("Tom");
			SampleCloneB sample2 = (SampleCloneB)sample1.clone();
			sample2.school = "Mouse school";
			sample2.student.add("Jerry");

			System.out.println("--------------------");

			System.out.println("sample1 = " + sample1);
			System.out.println("sample2 = " + sample2);
		} catch (Exception e) {

		}
	}

	public static void main(String[] args){
		TestClone testMain = new TestClone();

		System.out.println("============================================================");

		testMain.testA();

		System.out.println("============================================================");

		testMain.testB();

		System.out.println("============================================================");
	}
}

class SampleCloneA implements Cloneable
{
	private String name = "Disney";
	public String school = "Cat school";
	public LinkedList<String> student = new LinkedList<String>();

	@Override
	public Object clone() throws CloneNotSupportedException{
		// That LinkedList is only cloned by address.
		// Its content is not cloned.
		return super.clone();
	}

	@Override
	public String toString(){
		return "Sample [name=" + name + ", school=" + school + ", student=" + student + "]";
	}
}

class SampleCloneB implements Cloneable
{
	private String name = "Disney";
	public String school = "Cat school";
	public LinkedList<String> student = new LinkedList<String>();

	@Override
	public Object clone() throws CloneNotSupportedException{
		SampleCloneB sample = (SampleCloneB)super.clone();
		// That LinkedList and its content are cloned.
		sample.student = (LinkedList<String>)this.student.clone();
		return sample;
	}

	@Override
	public String toString(){
		return "Sample [name=" + name + ", school=" + school + ", student=" + student + "]";
	}
}
