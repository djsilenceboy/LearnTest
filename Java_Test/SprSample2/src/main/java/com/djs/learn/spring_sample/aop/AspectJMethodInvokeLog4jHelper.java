
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
 * Unlike defined in <aop:config>, there is no sequence effect while defining Before/AfterXXX/Around in Java class.<br>
 * <p>
 * In class,<br>
 * Around is always called after Before.<br>
 * Around is always called after AfterXXX.<br>
 */
@Aspect
public class AspectJMethodInvokeLog4jHelper
{
	protected static final Logger log = Logger.getLogger(AspectJMethodInvokeLog4jHelper.class);

	@Before("execution(* *..aop.*.deliver()) && target(bean)")
	public void beforeInvoke1(Object bean) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Bean = " + bean);
		}
	}

	@Before("execution(* *..aop.*.deliver(..)) && args(request,..) && target(bean)")
	public void beforeInvoke2(Object request, Object bean) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Bean = " + bean);
			log.debug("Request = " + request);
		}
	}

	@AfterReturning(pointcut = "execution(* *..aop.*.deliver(..)) and target(bean)", returning = "response")
	public void afterReturningInvoke(Object response, Object bean) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Bean = " + bean);
			log.debug("Response = " + response);
		}
	}

	@AfterThrowing(pointcut = "execution(* *..aop.*.deliver(..)) and target(bean)", throwing = "e")
	public void afterThrowingInvoke(Exception e, Object bean) throws Exception{
		if (log.isEnabledFor(Level.ERROR)) {
			log.error("Bean = " + bean);
			log.error("Exception = " + e, e);
		}
	}

	@After("execution(* *..aop.*.deliver(..)) and target(bean)")
	public void afterFinallyInvoke(Object bean) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Bean = " + bean);
		}
	}

	@Around("execution(* *..aop.*.deliver()) && target(bean)")
	public Object aroundInvoke1(ProceedingJoinPoint pjp, Object bean) throws Throwable{
		if (log.isDebugEnabled()) {
			log.error("Bean = " + bean);
		}

		Object response = pjp.proceed();

		if (log.isDebugEnabled()) {
			log.debug("Response = " + response);
		}

		return response;
	}

	/*
	 * No need to declare ProceedingJoinPoint or JoinPoint (if they are first parameter) in XML.
	 */
	@Around("execution(* *..aop.*.deliver(..)) && args(request,..) && target(bean)")
	public Object aroundInvoke2(ProceedingJoinPoint pjp, Object request, Object bean) throws Throwable{
		if (log.isDebugEnabled()) {
			log.error("Bean = " + bean);
			log.debug("Request = " + request);
		}

		Object response = pjp.proceed();

		if (log.isDebugEnabled()) {
			log.debug("Response = " + response);
		}

		return response;
	}
}
