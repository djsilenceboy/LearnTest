
package com.djs.learn.behavioral.state;

public class StateContext
{
	StateInterface currentState = new StateA();

	public StateContext(){
		System.out.println("Create state context.");
	}

	public void operation(String data){
		currentState = currentState.operation(data);
	}
}
