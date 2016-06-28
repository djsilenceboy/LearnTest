
package dj.test.se7test;

public class CloneTest
{
	public static void main(String[] args){
		Sample a = new Sample("s1", "s2");
		Sample b = new Sample("s3", "s4");
		b = a.clone();

		System.out.println(a.a + " " + a.b);
		System.out.println(b.a + " " + b.b);
	}
}

class Sample
{
	String a;
	String b;

	Sample(String a, String b){
		this.a = a;
		this.b = b;
	}

	@Override
	public Sample clone(){
		Sample a = this;

		return a;
	}
}
