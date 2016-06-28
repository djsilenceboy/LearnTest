
package com.djs.test.spring_sample;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.djs.test.spring_sample.db.Item;
import com.djs.test.spring_sample.db.ItemDao;
import com.djs.test.spring_sample.greeting.GreetingService;
import com.djs.test.spring_sample.knight.Knight;
import com.djs.test.spring_sample.music.Performer;
import com.djs.test.spring_sample.music.Stage;

public class AppTest
{
	private final Logger log = Logger.getLogger( AppTest.class );

	// private BeanFactory factory = null;
	private ApplicationContext appContext = null;

	@Before
	public void setUp()
	{
	}

	@Ignore
	@Test
	public void testGreeting()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( "test_greeting.xml" );

		GreetingService greetingService = (GreetingService)appContext.getBean( "greetingService" );
		greetingService.sayGreeting();
	}

	@Ignore
	@Test
	public void testKnight()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( "test_knight.xml" );

		try
		{
			Knight knightA = (Knight)appContext.getBean( "knightA" );
			knightA.embarkOnQuest();

			Knight knightB = (Knight)appContext.getBean( "knightB" );
			knightB.embarkOnQuest();
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testMusic()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( "test_music.xml" );

		try
		{
			Performer performerA = (Performer)appContext.getBean( "performerA" );
			performerA.perform();

			Performer performerB = (Performer)appContext.getBean( "performerB" );
			performerB.perform();

			Performer performerC = (Performer)appContext.getBean( "performerC" );
			performerC.perform();

			Performer performerD1 = (Performer)appContext.getBean( "performerD1" );
			performerD1.perform();

			Performer performerD2 = (Performer)appContext.getBean( "performerD2" );
			performerD2.perform();

			Stage stageA = (Stage)appContext.getBean( "stageA" );
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testDbJdbc()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( new String []
		{ "db_datasource.xml", "db_jdbc.xml" } );

		try
		{
			int id = (int)System.currentTimeMillis();
			final String namePrefix = "Jdbc_";

			// 0 - 5
			int from = 0;
			int to = 5;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				item.setSeqId( new Integer( id++ ) );
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testDbHibernate()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( new String []
		{ "db_datasource.xml", "db_hibernate.xml", "db_log.xml", "db_hibernate_log.xml" } );

		try
		{
			final String namePrefix = "Hibernate_";
			// 0 - 3
			// 3 is 1st recommended; 0 is 2nd recommended.
			int from = 1;
			int to = 1;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				// Hibernate will auto generate ID from sequence.
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );
				item.setAddTime( new Date() );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
				// Repeat to test caching.
				// If caching working, this call will not be totally bypassed! Not be called at all!
				itemDao.query( namePrefix + i );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	// @Ignore
	@Test
	public void testDbJpa()
	{
		log.trace( "Enter..." );

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( new String []
		{ "db_datasource.xml", "db_jpa.xml", "db_log.xml", "db_jpa_log.xml" } );

		try
		{
			final String namePrefix = "Jpa_";
			// 0 - 1
			int from = 0;
			int to = 0;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				// Hibernate will auto generate ID from sequence.
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );
				item.setAddTime( new Date() );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}
}
