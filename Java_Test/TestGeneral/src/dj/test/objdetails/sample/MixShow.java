
package dj.test.objdetails.sample;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public class MixShow
{
	public BigDecimal dataA = new BigDecimal(20.000123);
	public BigInteger dataB = new BigInteger("3123456");
	public Double dataC = new Double(20.00012345);

	public Date date = new Date();
	public Calendar calendar = Calendar.getInstance();

	public Date date2 = date;
	public Calendar calendar2 = calendar;

	public Exception e1 = new Exception("Find a bug!");
	public Exception e2 = new Exception("It is a bug!", e1);

	public MixShow local = this;

	public int counter = 21;

	public static String buyer = "John";
	public static String buyer2 = buyer;

	public final String buyer3 = buyer;
	public final String buyer4 = buyer;

	public Tv tv = new Tv();
	public Tv tv2 = tv;

	public static final Character STATUS_OK = new Character('O');
	public static final int KEY_VALUE_MAX_SIZE = 40;

	public InnerCar subCar = new InnerCar();
	public InnerTv subTv = new InnerTv();

	public static class InnerCar
	{
		public String brand = "Test";
		public int price = 102;

		@Override
		public String toString(){
			return "This is InnerCar " + brand;
		}
	}

	public class InnerTv
	{
		public String brand = "Test";
		public int price = 223;

		@Override
		public String toString(){
			return "This is InnerTv " + brand;
		}
	}
}
