
package dj.test.behavioral.visitor;

public class Visitor implements VisitorInterface
{
	String name;

	public Visitor(String name){
		this.name = name;
		System.out.println("Create visitor: " + name);
	}

	@Override
	public void process(ElementInterface element){
		System.out.println("Visitor " + name + ": Process element: " + element);
	}

	@Override
	public String toString(){
		return "Visitor [name=" + name + "]";
	}
}
