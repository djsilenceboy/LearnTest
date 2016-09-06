
package com.djs.learn.behavioral.chainOfResponsibility;

abstract class AbstractHandlerTypeB implements HandlerTypeBInterface
{
	String name;

	@Override
	public String getName(){
		return name;
	}
}
