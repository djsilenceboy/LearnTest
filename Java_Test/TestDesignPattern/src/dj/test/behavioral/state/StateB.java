
package dj.test.behavioral.state;

public class StateB implements StateInterface
{
	public StateB(){
		System.out.println("Create state B.");
	}

	@Override
	public StateInterface operation(String data){
		System.out.println("State B: " + data);
		System.out.println("State B -> State C");

		return new StateC();
	}
}
