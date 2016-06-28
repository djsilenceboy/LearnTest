
package com.djs.test.spring_sample.music;

import org.apache.log4j.Logger;

public class Stage
{
	private static final Logger log = Logger.getLogger( Stage.class );

	private static Stage instance = null;

	private Stage()
	{
		if (log.isDebugEnabled())
		{
			log.debug( "New stage." );
		}
	}

	public static Stage getInstance()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Get a stage." );
		}

		if (instance == null)
		{
			instance = new Stage();
		}

		return instance;
	}

	@Override
	public String toString()
	{
		return "stage";
	}
}
