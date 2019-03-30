
package com.djs.learn.behavioral.state;

public class StateC implements StateInterface
{
	public StateC(){
		System.out.println("Create state C.");
	}

	@Override
	public StateInterface operation(String data){
		System.out.println("State C: " + data);
		System.out.println("State C -> State A");

		return new StateA();
	}
}
