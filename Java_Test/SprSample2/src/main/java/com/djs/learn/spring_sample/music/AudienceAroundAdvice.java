
package com.djs.learn.spring_sample.music;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class AudienceAroundAdvice implements MethodInterceptor
{
	private final Logger log = Logger.getLogger( AudienceAroundAdvice.class );

	private Audience audience;

	public void setAudience( Audience audience )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign a " + audience + "." );
		}

		this.audience = audience;
	}

	public Object invoke( MethodInvocation invocation ) throws Throwable
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Invoke." );
		}

		try
		{
			audience.takeSeats();
			audience.turnOffCellPhones();

			Object returnValue = invocation.proceed();

			audience.applaud();

			return returnValue;
		}
		catch (PerformanceException throwable)
		{
			audience.demandRefund();

			throw throwable;
		}
	}

	@Override
	public String toString()
	{
		return "audience around advice";
	}
}
