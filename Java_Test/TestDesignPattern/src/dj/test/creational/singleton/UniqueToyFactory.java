
package dj.test.creational.singleton;

public class UniqueToyFactory
{
	static UniqueToy uniqueToy;

	public synchronized UniqueToy create(){
		if (uniqueToy == null) {
			uniqueToy = new UniqueToy();
			uniqueToy.setName("UniqueToy");
			uniqueToy.setType("Unique");

			System.out.println("Created unique toy:" + uniqueToy);
		}

		System.out.println("Retrieve unique toy:" + uniqueToy);

		return uniqueToy;
	}
}
