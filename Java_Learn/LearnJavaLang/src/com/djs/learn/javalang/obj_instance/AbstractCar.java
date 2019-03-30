
package com.djs.learn.javalang.obj_instance;

import java.io.Serializable;

public abstract class AbstractCar implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String getName(){
		return "Car";
	}
}
