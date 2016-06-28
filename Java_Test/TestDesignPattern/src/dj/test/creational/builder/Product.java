
package dj.test.creational.builder;

import dj.test.creational.common.ProductInterface;

public class Product
{
	String name;
	ProductInterface partA;
	ProductInterface partB;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public ProductInterface getPartA(){
		return partA;
	}

	public void setPartA(ProductInterface partA){
		this.partA = partA;
	}

	public ProductInterface getPartB(){
		return partB;
	}

	public void setPartB(ProductInterface partB){
		this.partB = partB;
	}

	@Override
	public String toString(){
		return "Product [name=" + name + ", partA=" + partA.getName() + ", partB=" + partB.getName() + "]";
	}
}
