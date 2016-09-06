
package com.djs.learn.javalang.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestCompareV8
{
	void testComparator(){
		List<Product> products = new ArrayList<Product>();

		products.add(new Product(50));
		products.add(new Product(10));
		products.add(new Product(20));
		products.add(new Product(40));

		System.out.println("products = " + products);

		Comparator<Product> comparator = (x, y) -> x.price - y.price;

		Collections.sort(products, comparator);

		System.out.println("products (sorted) = " + products);
	}

	public static void main(String[] args){
		TestCompareV8 testMain = new TestCompareV8();

		testMain.testComparator();
		System.out.println("--------------------------------------------------");
	}
}
