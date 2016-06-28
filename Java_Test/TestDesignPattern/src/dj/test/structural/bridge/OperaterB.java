
package dj.test.structural.bridge;

public class OperaterB implements OperaterInterface
{
	@Override
	public void process(String request){
		System.out.println("Operater B: " + request);
	}
}
