
package com.djs.learn.spring_sample.knight;

import org.apache.log4j.Logger;

public class Minstrel
{
	private final Logger log = Logger.getLogger( Minstrel.class );

	public void singBefore( Knight knight )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Sir " + knight.getName() + " is so brave!" );
		}
	}

	public void singAfter( Knight knight )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Sir " + knight.getName() + " did embark on a quest!" );
		}
	}
}
