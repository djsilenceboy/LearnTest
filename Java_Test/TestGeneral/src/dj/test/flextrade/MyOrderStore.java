
package dj.test.flextrade;

import java.util.List;

public class MyOrderStore implements OrderStore
{
	List<Order> orders;

	MyOrderStore(List<Order> orders){
		this.orders = orders;
	}

	@Override
	public List<Order> getOrders(){
		return orders;
	}
}
