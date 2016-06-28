
package dj.test.structural.decorator;

public class SimpleControl implements ControlInterface
{
	String name;

	public SimpleControl(String name){
		System.out.println("Create SimpleControl: " + name);

		this.name = name;
	}

	@Override
	public void process(){
		System.out.println("Process SimpleControl: " + name);
	}

	@Override
	public String getDescription(){
		return name;
	}
}
