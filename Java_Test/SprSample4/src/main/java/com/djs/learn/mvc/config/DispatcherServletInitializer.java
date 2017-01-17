
package com.djs.learn.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * This class Works as traditional "web.xml".
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	private static final Logger logger = Logger.getLogger(DispatcherServletInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses(){
		logger.info(this.getClass().getName() + ":getRootConfigClasses");
		return new Class[]{RootApplicationContextConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses(){
		logger.info(this.getClass().getName() + ":getServletConfigClasses");
		return new Class[]{WebApplicationContextConfig.class};
	}

	@Override
	protected String[] getServletMappings(){
		logger.info(this.getClass().getName() + ":getServletMappings");
		// This will append one more section to main context root.
		// return new String[]{"/app/*"};
		return new String[]{"/"};
	}
}
