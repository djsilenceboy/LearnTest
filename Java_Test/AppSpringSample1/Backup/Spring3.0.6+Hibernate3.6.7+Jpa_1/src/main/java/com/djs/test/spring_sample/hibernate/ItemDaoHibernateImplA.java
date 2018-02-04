
package com.djs.learn.spring_sample.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoHibernateImplA implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoHibernateImplA.class );

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate( HibernateTemplate hibernateTemplate )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + hibernateTemplate.getClass().getName() );
		}

		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save/Update: " + item );
		}

		// This saveOrUpdate() will do an update action first. If not exit, then do an insert action.
		// hibernateTemplate.saveOrUpdate( item );
		hibernateTemplate.save( item );
	}

	@Override
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "FROM " + Item.class.getName() + " WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = hibernateTemplate.find( sql, new Object []
		{ itemName } );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
