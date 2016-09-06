
package com.djs.learn.behavioral.chainOfResponsibility;

public interface HandlerTypeAInterface
{
	String getName();

	void setNextHandler(HandlerTypeAInterface nextHandler);

	String process(String content);
}
