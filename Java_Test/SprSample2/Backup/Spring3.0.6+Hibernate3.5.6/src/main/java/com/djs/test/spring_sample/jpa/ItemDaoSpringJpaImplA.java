
package com.djs.learn.spring_sample.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoSpringJpaImplA extends JpaDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringJpaImplA.class );

	@Override
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save/Update: " + item );
		}

		getJpaTemplate().persist( item );
	}

	@Override
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "SELECT a FROM " + Item.class.getName() + " a WHERE a.ITEM_NAME = ?1";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = getJpaTemplate().find( sql, itemName );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
