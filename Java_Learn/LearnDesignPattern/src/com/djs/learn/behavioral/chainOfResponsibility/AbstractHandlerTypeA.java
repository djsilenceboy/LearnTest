
package com.djs.learn.behavioral.chainOfResponsibility;

abstract class AbstractHandlerTypeA implements HandlerTypeAInterface
{
	String name;
	HandlerTypeAInterface nextHandler;

	@Override
	public String getName(){
		return name;
	}
}
