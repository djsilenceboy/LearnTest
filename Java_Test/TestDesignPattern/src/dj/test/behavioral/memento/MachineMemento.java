
package dj.test.behavioral.memento;

import java.util.Arrays;

public class MachineMemento
{
	String[] states;

	public MachineMemento(){
		System.out.println("Create machine memento.");
	}

	public String[] getStates(){
		return states;
	}

	public void setStates(String[] states){
		this.states = states;
	}

	@Override
	public String toString(){
		return "MachineMemento [states=" + Arrays.toString(states) + "]";
	}
}
