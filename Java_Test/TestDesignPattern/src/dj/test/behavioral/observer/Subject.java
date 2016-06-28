
package dj.test.behavioral.observer;

import java.util.LinkedList;
import java.util.List;

public class Subject
{
	List<ObserverInterface> observers = new LinkedList<ObserverInterface>();

	public Subject(){
		System.out.println("Create subject.");
	}

	public void register(ObserverInterface observer){
		observers.add(observer);
	}

	public void notify(String message){
		System.out.println("Subject message: " + message);

		for (ObserverInterface observer : observers) {
			observer.update(message);
		}
	}
}
