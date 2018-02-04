
package com.djs.learn.spring_sample.aop_tx;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class TravelInfoLogHelper
{
	protected static final Logger log = Logger.getLogger(TravelInfoLogHelper.class);

	public Object aroundInvoke(ProceedingJoinPoint pjp, TravelInfoLogInterface travelInfoLog) throws Throwable{
		TravelInfoDo travelInfoDo = travelInfoLog.getTravelInfoDo();

		if (log.isDebugEnabled()) {
			log.debug("Bean = " + travelInfoLog);
		}

		travelInfoDo.setStart(new Date());

		if (log.isTraceEnabled()) {
			log.trace("TravelInfoDo = " + travelInfoDo.hashCode());
			log.trace("Start time = " + travelInfoDo.getStart());
		}

		Object response = pjp.proceed();

		travelInfoDo.setStop(new Date());

		if (log.isTraceEnabled()) {
			log.trace("TravelInfoDo = " + travelInfoDo.hashCode());
			log.trace("Stop time = " + travelInfoDo.getStop());
			log.trace("Duration (ms) = " + (travelInfoDo.getStop().getTime() - travelInfoDo.getStart().getTime()));
		}

		return response;
	}
}
