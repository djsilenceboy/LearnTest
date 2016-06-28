
package dj.test.creational.builder;

public class Director
{
	public Director(){
		System.out.println("Create director");
	}

	public Product create(BuilderInterface builder){
		Product product = new Product();

		product.setName(builder.getName() + "'s product");
		product.setPartA(builder.createPartA());
		product.setPartB(builder.createPartB());

		System.out.println("Create product: " + product);

		return product;
	}
}
