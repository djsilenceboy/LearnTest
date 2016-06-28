
package com.djs.test.spring_sample.music;

import java.util.List;

import org.apache.log4j.Logger;

public class OneManBand implements Performer
{
	private final Logger log = Logger.getLogger( OneManBand.class );

	private final String name;
	private String song;
	private List<Instrument> instruments;

	public OneManBand( String name )
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

	public void setInstruments( List<Instrument> instruments )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign instruments " + instruments + "." );
		}

		this.instruments = instruments;
	}

	public List<Instrument> getInstruments()
	{
		return instruments;
	}

	@Override
	public void tuneInstrument()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is tuning " + instruments + "." );
		}

		for (Instrument item : instruments)
		{
			item.tune();
		}
	}

	@Override
	public void perform() throws PerformanceException
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is performing " + instruments + " for song \"" + song + "\"." );
		}

		for (Instrument item : instruments)
		{
			item.play();
		}
	}

	@Override
	public void cleanInstrument()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is cleaning " + instruments + "." );
		}

		for (Instrument item : instruments)
		{
			item.clean();
		}
	}

	@Override
	public String toString()
	{
		return "one man band " + name;
	}
}
