
package dj.test.structural.facade;

public class FacadeFactory
{
	public FacadeInterface getFacade(String name){
		return new FacadeImpl(name);
	}
}
