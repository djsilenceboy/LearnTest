
package com.djs.learn.behavioral.strategy;

public class StrategyContext
{
	StrategyInterface strategy = new StrategyA();
	String data1;
	String data2;

	public StrategyContext(String data1, String data2){
		this.data1 = data1;
		this.data2 = data2;

		System.out.println("Create StrategyContext: " + data1 + ", " + data2);
	}

	public void setStrategy(StrategyInterface strategy){
		System.out.println("Set new strategy.");
		if (strategy != null) {
			this.strategy = strategy;
		}
	}

	public void process(){
		strategy.process(data1, data2);
	}
}
