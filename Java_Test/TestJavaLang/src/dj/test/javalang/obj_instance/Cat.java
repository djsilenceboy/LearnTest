
package dj.test.javalang.obj_instance;

public class Cat extends AbstactAnimal
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getName(){
		return "Cat " + super.getName();
	}
}
