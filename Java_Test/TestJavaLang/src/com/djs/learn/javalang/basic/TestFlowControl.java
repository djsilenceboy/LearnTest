
package com.djs.learn.javalang.basic;

public class TestFlowControl
{
	final int INDEX_2 = 2;

	public void testSwitch(){
		{
			GameType gameType = GameType.RTS;

			switch (gameType){
				case RPG:
					System.out.println(gameType);
				break;
				case RTS:
					System.out.println(gameType);
				break;
				case AVG:
					System.out.println(gameType);
				break;
				default:
					System.out.println("Unknown");
			}
		}

		{
			int index = 2;

			switch (index){
				case 0:
					System.out.println(index);
				break;
				case 1:
					System.out.println(index);
				break;
				case INDEX_2:
					System.out.println(index);
				break;
				default:
					System.out.println("Unknown");
			}
		}

		{
			Integer index = 2;

			switch (index){
				case 0:
					System.out.println(index);
				break;
				case 1:
					System.out.println(index);
				break;
				case INDEX_2:
					System.out.println(index);
				break;
				default:
					System.out.println("Unknown");
			}
		}

		{
			char index = '2';

			switch (index){
				case 0:
					System.out.println(index);
				break;
				case 1:
					System.out.println(index);
				break;
				case INDEX_2:
					System.out.println(index);
				break;
				default:
					System.out.println("Unknown");
			}
		}
	}

	public void testSwitchFalldown(){
		{
			int index = 2;

			switch (index){
				case 0:
					System.out.println(0);
				case 1:
					System.out.println(1);
				case 2:
					System.out.println(2);
				case 3:
					System.out.println(3);
				default:
					System.out.println("Unknown");
			}
		}

		System.out.println("----------------------------------------");

		{
			// There is no particular order for default and case
			int index = 2;

			switch (index){
				case 0:
					System.out.println(0);
				default:
					System.out.println("Unknown");
				case 1:
					System.out.println(1);
				break;
				case 3:
					System.out.println(3);
			}
		}
	}

	public void testFor(){
		String[] names = {"Tom", "Jerry", "Mary"};
		String result = null;

		for (String name : names) {
			result = name;
		}

		System.out.println("Result = " + result);
	}

	public void testBreakContinue(){
		int i = 5;

		loop1:
		while (i-- > 0) {
			for (int j = 0; j < 5; j++) {
				if (j == 2) {
					break loop1;
				}

				System.out.println("i, j = " + i + ", " + j);
			}
		}

		System.out.println("----------------------------------------");

		i = 5;

		while (i-- > 0) {
			for (int j = 0; j < 5; j++) {
				if (j == 2) {
					break;
				}

				System.out.println("i, j = " + i + ", " + j);
			}
		}

		System.out.println("----------------------------------------");

		i = 5;

		loop2:
		while (i-- > 0) {
			for (int j = 0; j < 5; j++) {
				if (j == 2) {
					continue loop2;
				}

				System.out.println("i, j = " + i + ", " + j);
			}
		}

		System.out.println("----------------------------------------");

		i = 5;

		while (i-- > 0) {
			for (int j = 0; j < 5; j++) {
				if (j == 2) {
					continue;
				}

				System.out.println("i, j = " + i + ", " + j);
			}
		}
	}

	public void testAssert(){
		int x = 2;

		assert x == 2 : "[1] x != 2";

		x = 3;

		assert x == 2 : "[2] x != 2";
	}

	public static void main(String[] args){
		TestFlowControl testMain = new TestFlowControl();

		System.out.println("============================================================");

		testMain.testSwitch();

		System.out.println("============================================================");

		testMain.testSwitchFalldown();

		System.out.println("============================================================");

		testMain.testFor();

		System.out.println("============================================================");

		testMain.testBreakContinue();

		System.out.println("============================================================");

		testMain.testAssert();

		System.out.println("============================================================");
	}
}

enum GameType
{
	RPG, RTS, AVG
}
