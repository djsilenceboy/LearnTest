
package com.djs.learn.structural.bridge;

public class OperaterB implements OperaterInterface
{
	@Override
	public void process(String request){
		System.out.println("Operater B: " + request);
	}
}
