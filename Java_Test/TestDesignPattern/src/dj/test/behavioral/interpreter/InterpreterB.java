
package dj.test.behavioral.interpreter;

public class InterpreterB implements InterpreterInterface
{
	@Override
	public String interpreter(ContextData contextData){
		System.out.println("Use Interpreter B.");

		String result = contextData.getContext() + " is bad!";

		return result;
	}

}
