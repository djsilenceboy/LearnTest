
package com.djs.learn.structural.decorator;

public class DecoratorControl implements ControlInterface
{
	String name;
	ControlInterface simpleControl;

	public DecoratorControl(String name, ControlInterface simpleControl){
		System.out.println("Create DecoratorControl: " + name);

		this.name = name;
		this.simpleControl = simpleControl;
	}

	@Override
	public void process(){
		simpleControl.process();
		System.out.println("Process DecoratorControl: " + name);
	}

	@Override
	public String getDescription(){
		return name + "-" + simpleControl.getDescription();
	}
}
