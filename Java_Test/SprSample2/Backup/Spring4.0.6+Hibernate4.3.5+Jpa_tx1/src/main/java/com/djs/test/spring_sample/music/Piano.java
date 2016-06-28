
package com.djs.test.spring_sample.music;

import org.apache.log4j.Logger;

public class Piano implements Instrument
{
	private final Logger log = Logger.getLogger( Piano.class );

	public Piano()
	{
		if (log.isDebugEnabled())
		{
			log.debug( "New " + this + "." );
		}
	}

	@Override
	public void tune()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Tune " + this + "." );
		}
	}

	@Override
	public void play()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Play " + this + "." );
		}
	}

	@Override
	public void clean()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Clean " + this + "." );
		}
	}

	@Override
	public String toString()
	{
		return "piano";
	}
}
