
package dj.test.structural.proxy;

public class RealSubject implements SubjectInterface
{
	String name;
	int age;

	public String getName(){
		return name;
	}

	@Override
	public void setName(String name){
		System.out.println("RealSubject: Name: " + name);

		this.name = name;
	}

	public int getAge(){
		return age;
	}

	@Override
	public void setAge(int age){
		System.out.println("RealSubject: Age: " + age);

		this.age = age;
	}

	@Override
	public void process(){
		System.out.println("RealSubject: " + name + " / " + age);
	}
}
