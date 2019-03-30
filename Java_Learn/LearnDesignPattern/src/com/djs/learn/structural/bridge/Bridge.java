
package com.djs.learn.structural.bridge;

public class Bridge implements BridgeInterface
{
	OperaterInterface operater;

	public OperaterInterface getOperater(){
		return operater;
	}

	@Override
	public void setOperater(OperaterInterface operater){
		System.out.println("Bridge: " + operater);

		this.operater = operater;
	}

	@Override
	public void process(String request){
		System.out.println("Bridge: Process.");

		operater.process(request);
	}
}
