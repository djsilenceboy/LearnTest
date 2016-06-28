
package com.djs.test.spring_sample.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.djs.test.spring_sample.db.Item;
import com.djs.test.spring_sample.db.ItemDao;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class ItemDaoSpringJpaImplA extends JpaDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringJpaImplA.class );

	@Override
	@TriggersRemove(cacheName = "localCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Persist: " + item );
		}

		getJpaTemplate().persist( item );
	}

	@Override
	@Cacheable(cacheName = "localCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "SELECT a FROM " + Item.class.getName() + " a WHERE a.itemName = ?1";

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
