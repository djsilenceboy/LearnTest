/**
 * 
 */

package com.djs.learn.calculator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * @author dj
 */
public class ServletContextListenerTest implements ServletContextListener
{
	public static Logger log = Logger.getLogger( ServletContextListenerTest.class );

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized( ServletContextEvent arg0 )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed( ServletContextEvent arg0 )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}
}
