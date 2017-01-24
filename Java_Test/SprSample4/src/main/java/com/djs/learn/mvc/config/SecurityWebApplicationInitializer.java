
package com.djs.learn.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer
{
	private static final Logger logger = Logger.getLogger(SecurityWebApplicationInitializer.class);

	public SecurityWebApplicationInitializer(){
		logger.info("[SecurityWebApplicationInitializer]");
	}
}
