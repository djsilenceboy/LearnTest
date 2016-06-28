
package dj.test.objdetails.sample;

public enum TvBrand
{
	SONY("Sony", 1000), SHARP("Sharp", 800);

	private String brand;
	private int price;

	private TvBrand(String brand, int price){
		this.brand = brand;
		this.price = price;
	}

	public String getBrand(){
		return brand;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price = price;
	}
}
