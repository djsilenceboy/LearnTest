
package com.djs.learn.spring_sample.knight;

import org.apache.log4j.Logger;

import com.djs.learn.spring_sample.knightI.Talk;

public abstract class AbstractKnight implements Talk
{
	private final Logger log = Logger.getLogger( AbstractKnight.class );

	@Override
	public void talkMyName()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Name of Sir is " + this );
		}
	}

	@Override
	public void talkMyGreat()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Sir " + this + " is great!" );
		}
	}
}
