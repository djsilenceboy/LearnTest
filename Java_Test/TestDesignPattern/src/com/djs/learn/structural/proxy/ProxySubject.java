
package com.djs.learn.structural.proxy;

public class ProxySubject implements SubjectInterface
{
	SubjectInterface realSubject;

	public ProxySubject(){
		realSubject = new RealSubject();
	}

	@Override
	public void setName(String name){
		System.out.println("ProxySubject: Name: " + name);

		realSubject.setName(name);
	}

	@Override
	public void setAge(int age){
		System.out.println("ProxySubject: Age: " + age);

		realSubject.setAge(age);
	}

	@Override
	public void process(){
		System.out.println("ProxySubject: Process.");

		realSubject.process();
	}
}
