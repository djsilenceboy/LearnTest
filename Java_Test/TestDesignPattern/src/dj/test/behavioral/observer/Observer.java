
package dj.test.behavioral.observer;

public class Observer implements ObserverInterface
{
	String name;

	public Observer(String name){
		this.name = name;

		System.out.println("Create observer: " + name);
	}

	@Override
	public void update(String message){
		System.out.println("Observer " + name + ": " + message);
	}

	@Override
	public String toString(){
		return "Observer [name=" + name + "]";
	}
}
