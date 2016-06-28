
package dj.test.structural.facade;

class FacadeImpl implements FacadeInterface
{
	String name;

	public FacadeImpl(String name){
		System.out.println("FacadeImpl set name: " + name);

		this.name = name;
	}

	@Override
	public void setValue(String value){
		System.out.println("FacadeImpl " + name + ": set value: " + value);
	}
}
