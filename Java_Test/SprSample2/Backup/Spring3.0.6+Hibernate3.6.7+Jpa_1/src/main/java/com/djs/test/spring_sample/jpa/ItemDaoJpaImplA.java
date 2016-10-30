
package com.djs.learn.spring_sample.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaTemplate;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

public class ItemDaoJpaImplA implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoJpaImplA.class );

	private JpaTemplate jpaTemplate;

	public void setJpaTemplate( JpaTemplate jpaTemplate )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + jpaTemplate.getClass().getName() );
		}

		this.jpaTemplate = jpaTemplate;
	}

	@Override
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Persist: " + item );
		}

		jpaTemplate.persist( item );
	}

	@Override
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "SELECT a FROM " + Item.class.getName() + " a WHERE a.itemName = ?1";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = jpaTemplate.find( sql, itemName );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
