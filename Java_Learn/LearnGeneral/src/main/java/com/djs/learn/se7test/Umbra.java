
package com.djs.learn.se7test;

/*
 * It's not here
 * Catch a shadow
 */
public class Umbra
{
	private String Penumbra = "!";

	public static void main(String[] args){
		Umbra umbra = new Umbra();
		umbra.Penumbra = "Catch a shadow";
		umbra.chaseShadows();
	}

	private void chaseShadows(){
		Inner inn = new Inner();
		inn.getIt();
	}

	class Inner
	{
		String Penumbra = "It's not here";

		public void getIt(){
			System.out.println(Penumbra);
			System.out.println(Umbra.this.Penumbra);
		}
	}
}
