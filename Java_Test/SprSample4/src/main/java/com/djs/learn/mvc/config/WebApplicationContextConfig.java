
package com.djs.learn.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.djs.learn.mvc")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter
{
	private static final Logger logger = Logger.getLogger(WebApplicationContextConfig.class);

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		logger.info(this.getClass().getName() + ":getInternalResourceViewResolver");

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		// The path related to the root of WAR file.
		// The path related to the root of webapp defined in Eclipse/Maven.
		resolver.setPrefix("/WEB-INF/jsp/");
		// Only jsp files.
		resolver.setSuffix(".jsp");

		return resolver;
	}
}
