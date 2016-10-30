
package com.djs.learn.spring_sample.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class ItemDaoSpringHibernateImplB extends HibernateDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringHibernateImplB.class );

	@Override
	// @TriggersRemove(cacheName = "localCache", removeAll = true)
	@TriggersRemove(cacheName = "localCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save/Update: " + item );
		}

		// This saveOrUpdate() will do an update action first. If not exit, then do an insert action.
		// getHibernateTemplate().saveOrUpdate( item );
		getHibernateTemplate().save( item );
	}

	@Override
	@Cacheable(cacheName = "localCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "FROM " + Item.class.getName() + " WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = getHibernateTemplate().find( sql, new Object []
		{ itemName } );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
