
package dj.test.objdetails.sample;

import java.util.List;

public class Shop implements java.io.Serializable
{
	private String name;
	private String owner;
	private String[] staffs;
	private int[] staffSale;
	private Car[] cars;
	private List<Tv> tvs;
	private TvBrand tvBrand;
	private TvBrand[] tvBrands;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getOwner(){
		return owner;
	}

	public void setOwner(String owner){
		this.owner = owner;
	}

	public String[] getStaffs(){
		return staffs;
	}

	public void setStaffs(String[] staffs){
		this.staffs = staffs;
	}

	public int[] getStaffSale(){
		return staffSale;
	}

	public void setStaffSale(int[] staffSale){
		this.staffSale = staffSale;
	}

	public Car[] getCars(){
		return cars;
	}

	public void setCars(Car[] cars){
		this.cars = cars;
	}

	public List<Tv> getTvs(){
		return tvs;
	}

	public void setTvs(List<Tv> tvs){
		this.tvs = tvs;
	}

	public TvBrand getTvBrand(){
		return tvBrand;
	}

	public void setTvBrand(TvBrand tvBrand){
		this.tvBrand = tvBrand;
	}

	public TvBrand[] getTvBrands(){
		return tvBrands;
	}

	public void setTvBrands(TvBrand[] tvBrands){
		this.tvBrands = tvBrands;
	}
}
