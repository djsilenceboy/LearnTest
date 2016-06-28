
package dj.test.behavioral.strategy;

public class StrategyB implements StrategyInterface
{
	public StrategyB(){
		System.out.println("Create Strategy B.");
	}

	@Override
	public void process(String data1, String data2){
		System.out.println("Strategy B: " + data1 + " * " + data2);
	}
}
