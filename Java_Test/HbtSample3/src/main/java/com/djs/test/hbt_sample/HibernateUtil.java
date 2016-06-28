
package com.djs.test.hbt_sample;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class HibernateUtil
{
	private final static Logger log = Logger.getLogger( HibernateUtil.class );

	private static EntityManagerFactory emf;

	static
	{
		try
		{
			log.debug( "Before Persistence.createEntityManagerFactory()." );

			// Start EntityManagerFactory.
			emf = Persistence.createEntityManagerFactory( "hbt_sample" );
		}
		catch (Throwable e)
		{
			log.error( e, e );

			throw new ExceptionInInitializerError( e );
		}
	}

	public static EntityManagerFactory getEntityManagerFactory()
	{
		// Alternatively, you could look up in JNDI here.
		return emf;
	}

	public static void shutdown()
	{
		log.debug( "Before EntityManagerFactory.close()." );

		// Close caches and connection pools.
		getEntityManagerFactory().close();
	}
}
