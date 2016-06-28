
package dj.test.behavioral.state;

public class StateA implements StateInterface
{
	public StateA(){
		System.out.println("Create state A.");
	}

	@Override
	public StateInterface operation(String data){
		System.out.println("State A: " + data);
		System.out.println("State A -> State B");

		return new StateB();
	}
}
