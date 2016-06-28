
package dj.test.objdetails.sample;

public class BasicGoods implements Goods, java.io.Serializable
{
	private String brand;

	public String getBrand(){
		return brand;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}
}
