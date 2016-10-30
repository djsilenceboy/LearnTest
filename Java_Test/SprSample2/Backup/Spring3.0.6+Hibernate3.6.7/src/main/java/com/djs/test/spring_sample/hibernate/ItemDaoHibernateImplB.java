
package com.djs.learn.spring_sample.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

// This class does not work yet. Need further studying.
@Repository
public class ItemDaoHibernateImplB implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoHibernateImplB.class );

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory( SessionFactory sessionFactory )
	{
		this.sessionFactory = sessionFactory;
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
		sessionFactory.getCurrentSession().save( item );
	}

	@Override
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "FROM " + Item.class.getName() + " WHERE ITEM_NAME = :itemName";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		Query query = sessionFactory.getCurrentSession().createQuery( sql );

		query.setString( "itemName", itemName );

		List items = query.list();

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
