
package com.djs.learn.behavioral.chainOfResponsibility;

public class HandlerTypeA extends AbstractHandlerTypeA
{
	public HandlerTypeA(String name){
		this.name = name;

		System.out.println("Create handler: " + name);
	}

	@Override
	public void setNextHandler(HandlerTypeAInterface nextHandler){
		this.nextHandler = nextHandler;
	}

	@Override
	public String process(String content){
		String result;

		System.out.println("Handler <" + name + "> input: " + content);

		// Process input.
		content = ((content == null) ? "" : content + "-") + name;

		System.out.println("Handler <" + name + "> processed input: " + content);

		// Prepare output.
		result = (nextHandler != null) ? nextHandler.process(content) : content;

		System.out.println("Handler <" + name + "> inner output: " + result);

		// Process output.
		result = "[" + result + "]";

		System.out.println("Handler <" + name + "> processed output: " + result);

		return result;
	}
}
