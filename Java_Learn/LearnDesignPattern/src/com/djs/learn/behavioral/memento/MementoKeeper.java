
package com.djs.learn.behavioral.memento;

import java.util.Map;
import java.util.TreeMap;

public class MementoKeeper
{
	Map<String, MachineMemento> mementos = new TreeMap<String, MachineMemento>();

	public MementoKeeper(){
		System.out.println("Create memento keeper.");
	}

	public void addMemento(String name, MachineMemento memento){
		System.out.println("Memento keeper: Add memento: " + name + ", " + memento);

		mementos.put(name, memento);
	}

	public MachineMemento getMemento(String name){
		MachineMemento memento = mementos.get(name);

		System.out.println("Memento keeper: Get memento: " + name + ", " + memento);

		return memento;
	}
}
