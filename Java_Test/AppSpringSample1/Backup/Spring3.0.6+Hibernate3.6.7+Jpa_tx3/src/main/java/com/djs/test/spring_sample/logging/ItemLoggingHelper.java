
package com.djs.learn.spring_sample.logging;

import java.util.Date;

import org.apache.log4j.Logger;

import com.djs.learn.spring_sample.db.ItemDao;
import com.djs.learn.spring_sample.db.ItemLog;
import com.djs.learn.spring_sample.db.ItemLogDao;

public class ItemLoggingHelper
{
	private final Logger log = Logger.getLogger( ItemLoggingHelper.class );

	private ItemLogDao itemLogDao;

	public void setItemLogDao( ItemLogDao itemLogDao )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + itemLogDao.getClass().getName() );
		}

		this.itemLogDao = itemLogDao;
	}

	private void saveLog( String message )
	{
		ItemLog itemLog = new ItemLog();
		// Hibernate will auto generate ID from sequence: item.setSeqId();
		itemLog.setMessage( message );
		itemLog.setLogTime( new Date() );

		if (log.isInfoEnabled())
		{
			log.info( "Log: " + itemLog );
		}

		// Remove for testing:
		// itemLogDao.save( itemLog );
	}

	public void beforeSave( ItemDao item )
	{
		saveLog( "Before save(): " + item.getClass().getName() );
	}

	public void afterSave( ItemDao item )
	{
		saveLog( "After save(): " + item.getClass().getName() );
	}

	public void throwingSave( ItemDao item )
	{
		saveLog( "Throwing save(): " + item.getClass().getName() );
	}

	public void beforeQuery( ItemDao item )
	{
		saveLog( "Before query(): " + item.getClass().getName() );
	}

	public void afterQuery( ItemDao item )
	{
		saveLog( "After query(): " + item.getClass().getName() );
	}

	public void throwingQuery( ItemDao item )
	{
		saveLog( "Throwing query(): " + item.getClass().getName() );
	}
}
