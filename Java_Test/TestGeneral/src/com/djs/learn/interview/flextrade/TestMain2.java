
package com.djs.learn.interview.flextrade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMain2
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

	// Test orderStore is null.
	@Test(expected = NullPointerException.class)
	public void myTest1(){
		SimpleOrderManager orderManager = new SimpleOrderManager(null, orderWriter);

		orderManager.writeAllOrders();
	}

	// Test orderStore is empty.
	@Test(expected = InvalidOperationException.class)
	public void myTest2(){
		SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

		orderManager.writeAllOrders();
	}

	// Test orderStore is not null and not empty, but orderWriter is null.
	@Test(expected = NullPointerException.class)
	public void myTest3(){
		orders.add(new Order());

		SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, null);

		orderManager.writeAllOrders();
	}

	// Test orderStore is not null but not empty, and orderWriter is not null.
	@Test
	public void myTest4(){
		try {
			SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

			orderManager.writeAllOrders();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "No orders in store");
		}
	}

	// Test orderStore is not null and not empty, and orderWriter is not null.
	@Test
	public void myTest5(){
		orders.add(new Order());
		orders.add(new Order());

		SimpleOrderManager orderManager = new SimpleOrderManager(orderStore, orderWriter);

		orderManager.writeAllOrders();
	}
}
