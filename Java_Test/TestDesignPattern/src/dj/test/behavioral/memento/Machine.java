
package dj.test.behavioral.memento;

import java.util.Arrays;

public class Machine
{
	String[] states;

	public Machine(){
		System.out.println("Create machine.");
	}

	public MachineMemento createMemento(){
		MachineMemento memento = new MachineMemento();

		memento.setStates(states);

		System.out.println("Machine: Create memento: " + memento);

		return memento;
	}

	public void restoreMemento(MachineMemento memento){
		System.out.println("Machine: Restore memento: " + memento);

		states = memento.getStates();
	}

	public String[] getStates(){
		return states;
	}

	public void setStates(String[] states){
		this.states = states;
	}

	@Override
	public String toString(){
		return "Machine [states=" + Arrays.toString(states) + "]";
	}
}
