
package dj.test.creational.common;


abstract class AbstractProduct implements ProductInterface
{
	ProductType productType;
	String name;

	@Override
	public ProductType getType(){
		return productType;
	}

	@Override
	public String getName(){
		return name;
	}
}
