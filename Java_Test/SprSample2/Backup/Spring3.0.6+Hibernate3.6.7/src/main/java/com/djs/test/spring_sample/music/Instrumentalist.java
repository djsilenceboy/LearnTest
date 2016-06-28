
package com.djs.test.spring_sample.music;

import org.apache.log4j.Logger;

public class Instrumentalist implements Performer
{
	private final Logger log = Logger.getLogger( Instrumentalist.class );

	private final String name;
	private String song;
	private Instrument instrument;

	public Instrumentalist( String name )
	{
		this.name = name;

		if (log.isDebugEnabled())
		{
			log.debug( "New " + this + "." );
		}
	}

	public void setSong( String song )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign a song \"" + song + "\"." );
		}

		this.song = song;
	}

	public String getSong()
	{
		return song;
	}

	public void setInstrument( Instrument instrument )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign an instrument " + instrument + "." );
		}

		this.instrument = instrument;
	}

	public Instrument getInstrument()
	{
		return instrument;
	}

	@Override
	public void tuneInstrument()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is tuning " + instrument + "." );
		}

		instrument.tune();
	}

	@Override
	public void perform() throws PerformanceException
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is performing " + instrument + " for song \"" + song + "\"." );
		}

		instrument.play();
	}

	@Override
	public void cleanInstrument()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is cleaning " + instrument + "." );
		}

		instrument.clean();
	}

	@Override
	public String toString()
	{
		return "instrumentalist " + name;
	}
}
