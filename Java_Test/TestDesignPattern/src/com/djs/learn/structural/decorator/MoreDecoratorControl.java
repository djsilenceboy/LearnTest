
package com.djs.learn.structural.decorator;

public class MoreDecoratorControl implements ControlInterface
{
	String name;
	ControlInterface simpleControl;

	public MoreDecoratorControl(String name, ControlInterface simpleControl){
		System.out.println("Create MoreDecoratorControl: " + name);

		this.name = name;
		this.simpleControl = simpleControl;
	}

	@Override
	public void process(){
		simpleControl.process();
		System.out.println("Process MoreDecoratorControl: " + name);
	}

	@Override
	public String getDescription(){
		return name + "-" + simpleControl.getDescription();
	}
}
