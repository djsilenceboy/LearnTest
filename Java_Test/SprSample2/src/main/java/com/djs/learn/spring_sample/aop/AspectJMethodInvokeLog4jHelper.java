
package com.djs.learn.spring_sample.aop;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Unlike defined in <aop:config>, there is no sequence effect while defining before/after/around in Java class.<br>
 * <p>
 * In <aop:config>, please note the position of around().<br>
 * If around() is before before(), it will be called before before().<br>
 * If around() is after before(), it will be called after before().<br>
 * If around() is before after(), it will be called before after().<br>
 * If around() is after after(), it will be called after after().<br>
 * <p>
 * In class,<br>
 * around() is always called after before().<br>
 * around() is always called after after().<br>
 */
@Aspect
public class AspectJMethodInvokeLog4jHelper
{
	protected static final Logger log = Logger.getLogger( AspectJMethodInvokeLog4jHelper.class );

	@Before("execution(* *..aop.*.deliver(..)) && args(request,..) && target(bean)")
	public void beforeInvoke( Object request, Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
			log.debug( "Request = " + request );
		}
	}

	@Before("execution(* *..aop.*.deliver()) && target(bean)")
	public void beforeInvoke2( Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
		}
	}

	@AfterReturning(pointcut = "execution(* *..aop.*.deliver(..)) and target(bean)", returning = "response")
	public void afterReturningInvoke( Object response, Object bean ) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug( "Bean = " + bean );
			log.debug( "Response = " + response );
		}
	}

	@AfterThrowing(pointcut = "execution(* *..aop.*.deliver(..)) and target(bean)", throwing = "e")
	public void afterThrowingInvoke( Exception e, Object bean ) throws Exception
	{
		if (log.isEnabledFor( Level.ERROR ))
		{
			log.error( "Bean = " + bean );
			log.error( "Exception = " + e, e );
		}
	}

	@After("execution(* *..aop.*.deliver(..)) and target(bean)")
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
	@Around("execution(* *..aop.*.deliver(..)) && args(request,..) && target(bean)")
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

	@Around("execution(* *..aop.*.deliver()) && target(bean)")
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
