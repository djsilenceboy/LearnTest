
package dj.test.behavioral.visitor;

public class Element implements ElementInterface
{
	String name;

	public Element(String name){
		this.name = name;
		System.out.println("Create element: " + name);
	}

	@Override
	public void acceptVisitor(VisitorInterface visitor){
		System.out.println("Element " + name + ": Accept visitor: " + visitor);

		visitor.process(this);
	}

	@Override
	public String toString(){
		return "Element [name=" + name + "]";
	}
}
