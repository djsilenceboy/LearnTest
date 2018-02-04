
package com.djs.learn.spring_sample.aop;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class MethodInvokeLog4jHelper
{
	protected static final Logger log = Logger.getLogger( MethodInvokeLog4jHelper.class );

	public void beforeInvoke( Object request, Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
			log.debug( "Request = " + request );
		}
	}

	public void beforeInvoke2( Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
		}
	}

	public void afterReturningInvoke( Object response, Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
			log.debug( "Response = " + response );
		}
	}

	public void afterThrowingInvoke( Exception e, Object bean ) throws Exception
	{
		if (log.isEnabledFor( Level.ERROR ))
		{
			log.error( "Bean = " + bean );
			log.error( "Exception = " + e, e );
		}
	}

	public void afterFinallyInvoke( Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
		}
	}

	/*
	 * No need to declare ProceedingJoinPoint or JoinPoint (if they are first parameter) in XML.
	 */
	public Object aroundInvoke( ProceedingJoinPoint pjp, Object request, Object bean ) throws Throwable
	{
		if (log.isDebugEnabled())
		{
			log.error( "Bean = " + bean );
			log.debug( "Request = " + request );
		}

		Object response = pjp.proceed();

		if (log.isDebugEnabled())
		{
			log.debug( "Response = " + response );
		}

		return response;
	}

	public Object aroundInvoke2( ProceedingJoinPoint pjp, Object bean ) throws Throwable
	{
		if (log.isDebugEnabled())
		{
			log.error( "Bean = " + bean );
		}

		Object response = pjp.proceed();

		if (log.isDebugEnabled())
		{
			log.debug( "Response = " + response );
		}

		return response;
	}
}
