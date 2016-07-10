
package dj.test.javalang.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class TestObjectIo
{
	String fileName = "target/data/SampleObject.txt";

	public static byte[] getBytesFromObject(Object obj) throws IOException{
		if (obj != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			oos.writeObject(obj);
			oos.close();

			return baos.toByteArray();
		} else {
			return null;
		}
	}

	public static Object getObjectFromBytes(byte[] data) throws IOException, ClassNotFoundException{
		if (data != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);

			return ois.readObject();
		} else {
			return null;
		}
	}

	public void testObjectOutputStream(){
		System.out.println("Test = ObjectOutputStream(FileOutputStream)");
		System.out.println("--------------------");

		File file = new File(fileName);

		System.out.println("File name = " + file.getPath());

		Person person = new Person(18);
		Person.total = 1;

		System.out.println("Person = " + person);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(person);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testObjectInputStream(){
		System.out.println("Test = ObjectInputStream(FileInputStream)");
		System.out.println("--------------------");

		File file = new File(fileName);

		System.out.println("File name = " + file.getPath());

		Person person = null;

		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file))) {
			// Person constructor and init codes will not be invoked.
			person = (Person)oos.readObject();
			Person.total++;

			System.out.println("Person = " + person);

			// There will be "java.io.EOFException".
			person = (Person)oos.readObject();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testByteArrayStream(){
		System.out.println("Test = ObjectOutputStream(ByteArrayOutputStream)");
		System.out.println("Test = ObjectInputStream(ByteArrayInputStream)");
		System.out.println("--------------------");

		Person person = new Person(29);
		Person.total++;

		System.out.println("Person = " + person);

		try {
			byte[] data = getBytesFromObject(person);

			System.out.println("Object data (" + data.length + ") = " + Arrays.toString(data));

			Object obj = getObjectFromBytes(data);

			System.out.println("Object is Person = " + (obj instanceof Person));
			System.out.println("Person = " + obj);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestObjectIo test = new TestObjectIo();

		System.out.println("========================================");

		test.testObjectOutputStream();

		System.out.println("========================================");

		test.testObjectInputStream();

		System.out.println("========================================");

		test.testByteArrayStream();

		System.out.println("========================================");
	}
}

class Person implements Serializable
{
	public int age = -1;
	private int age2 = -1;
	public transient String pet = "Cat";
	public transient int petCount = 1;
	public static int total = -1;
	public Student student = new Student(7);

	{
		Student.total = 1;
		System.out.println("Person: Some init code.");
	}

	public Person(){
		System.out.println("Person()");
	}

	public Person(int age){
		System.out.println("Person(age)");
		this.age = age;
		this.age2 = age;
	}

	@Override
	public String toString(){
		return "Person [age=" + age + ", age2=" + age2 + ", pet=" + pet + ", petCount=" + petCount + ", student=" + student + ", total=" + total + "]";
	}
}

class Student implements Serializable
{
	public int age = -1;
	private int age2 = -1;
	public transient String pet = "Cat";
	public transient int petCount = 1;
	public static int total = -1;

	{
		System.out.println("Student: Some init code.");
	}

	public Student(){
		System.out.println("Student()");
	}

	public Student(int age){
		System.out.println("Student(age)");
		this.age = age;
		this.age2 = age;
	}

	@Override
	public String toString(){
		return "Student [age=" + age + ", age2=" + age2 + ", pet=" + pet + ", petCount=" + petCount + ", total=" + total + "]";
	}
}
