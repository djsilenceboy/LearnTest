
package com.djs.learn.common;

public class TestDivideBounty
{
	private void divide(int level, double totalShare, double levelSharePer){
		int levelPerson = 1;
		for (int i = 1; i <= level; i++) {
			System.out.println("Level = " + i);
			System.out.println("Level person = " + levelPerson);
			System.out.println("Total share = " + totalShare);
			double levelShare = totalShare * levelSharePer;
			System.out.println("Level share = " + levelShare);
			totalShare = (totalShare - levelShare) / 2;
			levelPerson += levelPerson;
		}
	}

	public static void main(String[] args){
		TestDivideBounty test = new TestDivideBounty();

		System.out.println("================================================================================");
		test.divide(15, 100.0, 0.01);
		System.out.println("------------------------------------------------------------");
		test.divide(15, 10000000.0, 0.01);
		System.out.println("================================================================================");
	}
}
