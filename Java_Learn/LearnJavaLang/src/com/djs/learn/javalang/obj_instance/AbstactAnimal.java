
package com.djs.learn.javalang.obj_instance;

import java.io.Serializable;

public abstract class AbstactAnimal implements AnimalInterface, Serializable
{
	private static final long serialVersionUID = 1L;

	public String getName(){
		return "Animal";
	}
}
