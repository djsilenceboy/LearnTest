/**
 * 
 */

package com.djs.learn.calculator;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.log4j.Logger;

/**
 * @author dj
 */
public class ServletRequestListenerTest implements ServletRequestListener
{
	public static Logger log = Logger.getLogger( ServletRequestListenerTest.class );

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestInitialized( ServletRequestEvent arg0 )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
	 */
	@Override
	public void requestDestroyed( ServletRequestEvent arg0 )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}
}
