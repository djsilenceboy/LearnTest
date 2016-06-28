
package com.djs.test.spring_sample.db;

import java.util.List;


public interface ItemDao
{
	public void save( Item item );

	public List<Item> query( String itemName );
}
