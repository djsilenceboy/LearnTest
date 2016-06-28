
package dj.test.structural.composite;

public class SmallToy implements ToyInterface
{
	String name;

	public SmallToy(String name){
		System.out.println("Create SmallToy: " + name);

		this.name = name;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public void build(){
		System.out.println("Build SmallToy: " + name);
	}
}
