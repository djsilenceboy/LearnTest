
package com.djs.test.spring_sample.music;

import org.apache.log4j.Logger;

public class Saxophone implements Instrument
{
	private final Logger log = Logger.getLogger( Saxophone.class );

	public Saxophone()
	{
		if (log.isDebugEnabled())
		{
			log.debug( "New saxophone..." );
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
		return "saxophone";
	}
}
