
package com.djs.learn.spring_sample.music;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class AudienceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice
{
	private final Logger log = Logger.getLogger( AudienceAdvice.class );

	private Audience audience;

	public AudienceAdvice()
	{
		if (log.isDebugEnabled())
		{
			log.debug( "New " + this + "." );
		}
	}

	public void before( Method method, Object [] args, Object target ) throws Throwable
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " before " + audience + "." );
		}

		audience.takeSeats();
		audience.turnOffCellPhones();
	}

	public void afterReturning( Object returnValue, Method method, Object [] args, Object target ) throws Throwable
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " afterReturning " + audience + "." );
		}

		audience.applaud();
	}

	public void afterThrowing( Throwable throwable )
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " afterThrowing " + audience + "." );
		}

		audience.demandRefund();
	}

	public void setAudience( Audience audience )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign a " + audience + "." );
		}

		this.audience = audience;
	}

	@Override
	public String toString()
	{
		return "audience advice";
	}
}