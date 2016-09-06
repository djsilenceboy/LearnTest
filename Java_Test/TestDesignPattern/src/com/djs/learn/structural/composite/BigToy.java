
package com.djs.learn.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class BigToy implements ToyInterface
{
	String name;
	List<ToyInterface> smallToys = new ArrayList<ToyInterface>();

	public BigToy(String name){
		System.out.println("Create BigToy: " + name);

		this.name = name;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public void build(){
		for (ToyInterface toy : smallToys) {
			toy.build();
		}

		System.out.println("Build BigToy: " + name);
	}

	public void addSmallToy(ToyInterface smallToy){
		System.out.println("Add SmallToy: " + smallToy.getName());

		smallToys.add(smallToy);
	}
}
