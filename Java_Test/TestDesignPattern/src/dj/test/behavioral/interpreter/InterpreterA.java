
package dj.test.behavioral.interpreter;

public class InterpreterA implements InterpreterInterface
{
	@Override
	public String interpreter(ContextData contextData){
		System.out.println("Use Interpreter A.");

		String result = contextData.getContext() + " is good!";

		return result;
	}

}
