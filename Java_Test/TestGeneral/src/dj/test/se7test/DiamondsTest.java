
package dj.test.se7test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiamondsTest
{
	public static void main(String[] args){
		List takeIt = new ArrayList();
		Set<Integer> takeTwo = new HashSet<>();

		takeIt.add(47);
		takeIt.add("forty seven");
		takeIt.add(3.14);
		takeIt.add(2 / 3.0);

		while (!takeIt.isEmpty()) {
			Object taken = takeIt.remove(0);
			System.out.println(taken);

			try {
				takeTwo.add((Integer)taken);
			} catch (ClassCastException ex) {
				System.out.println("Generic type does not accept raw" + " type: " + taken.getClass().getSimpleName());
			}
		}
	}
}
