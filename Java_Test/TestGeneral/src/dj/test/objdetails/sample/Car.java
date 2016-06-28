
package dj.test.objdetails.sample;

import java.util.Calendar;
import java.util.Date;

public class Car extends MoreGoods
{
	private Integer price;
	private int[] ccs;
	private Date buyDate;
	private Calendar saleDate;

	public Integer getPrice(){
		return price;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public int[] getCcs(){
		return ccs;
	}

	public void setCcs(int[] ccs){
		this.ccs = ccs;
	}

	public Date getBuyDate(){
		return buyDate;
	}

	public void setBuyDate(Date buyDate){
		this.buyDate = buyDate;
	}

	public Calendar getSaleDate(){
		return saleDate;
	}

	public void setSaleDate(Calendar saleDate){
		this.saleDate = saleDate;
	}

	@Override
	public String toString(){
		return "This is Car " + getBrand();
	}
}
