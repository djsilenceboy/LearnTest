
package com.djs.learn.objdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.djs.learn.objdetails.sample.Car;
import com.djs.learn.objdetails.sample.Goods;
import com.djs.learn.objdetails.sample.MixShow;
import com.djs.learn.objdetails.sample.Shop;
import com.djs.learn.objdetails.sample.Tv;
import com.djs.learn.objdetails.sample.TvBrand;
import com.djs.learn.objdetails.sample.MixShow.InnerCar;

public class TestMain
{
	public static void main(String[] args){
		try {
			Exception e1 = new Exception("Find a bug!");
			Exception e2 = null;

			for (int i = 0; i < 20; i++) {
				e2 = new Exception("It is bug " + i + "!", e2);
			}

			System.out.println(ObjectAnalyzer.analyze(e2));
			System.out.println("----------------------------------------");
			System.out.println(CommonJavaHelper.getThrowableStackTrace(e2));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, CommonToStringHelper.getIgnoredFieldsArrayForThrowable(), null, 0,
			                                                                      0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, CommonToStringHelper.getIgnoredFieldsArrayForThrowable(), null, 20,
			                                                                      10));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, null, null, 4000, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, null, null, 4000, 2000));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, null, null, -1, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(e2, null, null, null, -1, -1));
			System.out.println(CommonToStringHelper.objectDeepToString_Throwable_Readable(e1, null, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_Throwable_Readable(e2, null, 400));
			System.out.println(CommonToStringHelper.objectDeepToString_Throwable_Readable(e2, null, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_Throwable_Readable(e2, null, -1));
			System.out.println("----------------------------------------");

			String[] staffs = new String[]{"Tom", "Jerry"};
			int[] staffSale = new int[]{80, 100};

			Car[] cars = new Car[2];
			cars[0] = new Car();
			cars[0].setBrand("BMW");
			cars[0].setTypes(new String[]{"GirlA", "GirlB"});
			cars[0].setPrice(100000);
			cars[0].setCcs(new int[]{800, 1000});
			cars[0].setBuyDate(new Date());
			cars[0].setSaleDate(Calendar.getInstance());
			cars[1] = new Car();
			cars[1].setBrand("Toyota");
			// cars[1].setTypes( new String [] { "BoyA", "BoyB" } );
			// cars[1].setPrice( 80000 );
			cars[1].setCcs(new int[]{400, 600});
			cars[1].setBuyDate(new Date());
			cars[1].setSaleDate(Calendar.getInstance());

			Goods gds1 = cars[0];

			List<Tv> tvs = new ArrayList<Tv>();
			Tv temp = new Tv();
			temp.setBrand("Sharp");
			temp.setTypes(new String[]{"Aquos 100", "Aquos 200"});
			temp.setPrices(new Integer[]{1000, 1200});
			tvs.add(temp);
			temp = new Tv();
			temp.setBrand("Sony");
			temp.setTypes(new String[]{"Viro A10", "Viro A20"});
			temp.setPrices(new Integer[]{1500, 2000});
			tvs.add(temp);

			Goods gds2 = tvs.get(0);

			TvBrand[] tvBrands = new TvBrand[]{TvBrand.SHARP, TvBrand.SONY};

			Shop shop = new Shop();
			shop.setName("Courts");
			// shop.setOwner( "You" );
			shop.setStaffs(staffs);
			shop.setStaffSale(staffSale);
			shop.setCars(cars);
			shop.setTvs(tvs);
			shop.setTvBrand(TvBrand.SONY);
			shop.setTvBrands(tvBrands);

			String[] excludedFields1 = new String[]{"price"};
			String[] excludedFields2 = new String[]{"types"};
			String[] excludedFields3 = new String[]{"price", "prices", "types"};

			MixShow mixShow = new MixShow();

			System.out.println("----------------------------------------");
			Class theClass = Shop.class;
			System.out.println(theClass.getCanonicalName());
			System.out.println(theClass.getName());
			System.out.println(theClass.getSimpleName());
			System.out.println(theClass.getPackage().getName());
			System.out.println("----------------------------------------");
			System.out.println(ObjectAnalyzer.analyze(cars[0]));
			System.out.println(ObjectAnalyzer.analyze(shop));
			System.out.println(ObjectAnalyzer.analyze(new InnerCar()));
			System.out.println("----------------------------------------");
			// System.out.println( ObjectUtils.toString( shop ) );
			System.out.println("----------------------------------------");
			System.out.println(Arrays.deepToString(staffs));
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(staffs, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(staffs, null, null, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(staffSale, null, null, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(cars[0], null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(cars[0], null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(gds1, null, null, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(gds2, null, null, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(tvBrands, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(tvBrands, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(tvBrands, null, null, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(Arrays.deepToString(cars));
			System.out.println(ArrayUtils.toString(cars));
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(cars, new String[]{"|", "null", "ref", "yyyyMMddHHmmssSSSZ", "#.###",
			                                                                                          "exclude", "notinclude"}, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(cars,
			                                                                    new String[]{null, "#Nil#", null, null, "#.########", "#Exc#", "#NotInc#"},
			                                                                    null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(cars, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_3_ReadMore(cars, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_4_Verbose(cars, new String[]{null, "#Nil#", null, null, "#.########", "#Exc#",
			                                                                                        "#NotInc#"}, excludedFields1, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_5_Trivial(cars, null, excludedFields1, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(tvs, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(tvs, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(tvs, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_3_ReadMore(tvs, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_4_Verbose(tvs, null, excludedFields2, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_5_Trivial(tvs, null, excludedFields2, null, 0, 0));
			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(shop, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(shop, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(shop, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_3_ReadMore(shop, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_4_Verbose(shop, null, excludedFields3, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_5_Trivial(shop, null, excludedFields3, null, 0, 0));
			System.out.println("----------------------------------------");

			String[] excludedFields4 = new String[]{"price", "buyDate", "saleDate"};
			String[] includedFields1 = new String[]{"cars", "price", "types"};

			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_3_ReadMore(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_4_Verbose(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_5_Trivial(shop, null, excludedFields4, includedFields1, 0, 0));
			System.out.println("----------------------------------------");

			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(null, null, null, null, 0, 0));

			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(mixShow, new String[]{null, null, null, "yyyyMMddHHmmssSSSZ", "#", null,
			                                                                                             null}, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(mixShow, new String[]{null, null, "REF", null, "#.###########", null, null},
			                                                                       null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_0_ValueOnly(mixShow, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(mixShow, null,
			                                                                      CommonToStringHelper.IGNORED_FIELDS_FOR_THROWABLE.toArray(new String[0]),
			                                                                      null, 0, 0));

			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(new Integer(10), null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new Integer(10), null, null, null, 0, 0));

			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(new Integer[]{}, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new Integer[]{}, null, null, null, 0, 0));

			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(new Integer[]{10, 20}, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new Integer[]{10, 20}, null, null, null, 0, 0));

			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(new int[]{}, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new int[]{}, null, null, null, 0, 0));

			System.out.println(CommonToStringHelper.objectDeepToString_1_Simple(new int[]{10, 20}, null, null, null, 0, 0));
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new int[]{10, 20}, null, null, null, 0, 0));

			System.out.println("----------------------------------------");
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(new InnerCar(), null, null, null, 0, 0));
			System.out.println((new InnerCar()).getClass().getModifiers());
			System.out.println((new InnerCar()).getClass().getName());

			System.out.println("----------------------------------------");
			MixShow.InnerTv innerTv = mixShow.new InnerTv();
			System.out.println(CommonToStringHelper.objectDeepToString_2_Readable(innerTv, null, null, null, 0, 0));
			System.out.println(innerTv.getClass().getModifiers());
			System.out.println(innerTv.getClass().getName());
		} catch (Exception e) {
			System.err.println("Exception = " + CommonToStringHelper.objectDeepToString_Throwable_Readable(e, null, 0));

			throw e;
		}
	}
}
