
package com.djs.learn.behavioral.strategy;

public class StrategyA implements StrategyInterface
{
	public StrategyA(){
		System.out.println("Create Strategy A.");
	}

	@Override
	public void process(String data1, String data2){
		System.out.println("Strategy A: " + data1 + " + " + data2);
	}
}
