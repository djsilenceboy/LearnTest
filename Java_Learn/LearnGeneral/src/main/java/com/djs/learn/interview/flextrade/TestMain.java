
package com.djs.learn.interview.flextrade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMain
{
	List<Order> orders;
	OrderStore orderStore;
	OrderWriter orderWriter;

	@Before
	public void init(){
		orders = new ArrayList<>();
		orderStore = new MyOrderStore(orders);
		orderWriter = new MyOrderWriter();
	}

	// Test orderStore is empty, then throw correct exception.
	@Test(expected = InvalidOperationException.class)
	public void myTest1(){
		SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

		orderManager.writeAllOrders();
	}

	// Test orderStore is empty, and the exception message is correct.
	@Test
	public void myTest2(){
		try {
			SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

			orderManager.writeAllOrders();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No orders in store");
		}
	}

	// Test orderStore is not empty, and no exception.
	@Test
	public void myTest3(){
		orders.add(new Order());
		orders.add(new Order());

		SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

		orderManager.writeAllOrders();
	}
}
