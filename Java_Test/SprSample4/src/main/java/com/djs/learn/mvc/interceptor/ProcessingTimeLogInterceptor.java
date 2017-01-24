
package com.djs.learn.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProcessingTimeLogInterceptor implements HandlerInterceptor
{
	private static final Logger logger = Logger.getLogger(ProcessingTimeLogInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		logger.info("[preHandle]");

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
		logger.info("[postHandle]");

		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String path = request.getRequestURL() + queryString;

		long startTime = (Long)request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();

		logger.info(String.format("%s millisecond taken to process the request %s.", (endTime - startTime), path));
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exceptionIfAny){
		// NO operation.
	}
}
