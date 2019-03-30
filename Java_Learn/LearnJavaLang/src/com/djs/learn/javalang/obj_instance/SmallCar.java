
package com.djs.learn.javalang.obj_instance;

public class SmallCar extends AbstractCar
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getName(){
		return "Small " + super.getName();
	}
}
