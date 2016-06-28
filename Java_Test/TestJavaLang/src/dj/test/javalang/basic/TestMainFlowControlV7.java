
package dj.test.javalang.basic;

public class TestMainFlowControlV7
{
	final String ORANGE = "Orange";

	public void testSwitch(String fruit){
		switch (fruit){
			case "Apple":
				System.out.println(fruit);
			break;
			case ORANGE:
				System.out.println(fruit);
			break;
			default:
				System.out.println("Unknown");
		}
	}

	public void testSwitch(){
		String fruit = "Apple";

		testSwitch(fruit);

		testSwitch(ORANGE);

		testSwitch(ORANGE.toLowerCase());
	}

	public static void main(String[] args){
		TestMainFlowControlV7 testMain = new TestMainFlowControlV7();

		System.out.println("============================================================");

		testMain.testSwitch();

		System.out.println("============================================================");
	}
}
