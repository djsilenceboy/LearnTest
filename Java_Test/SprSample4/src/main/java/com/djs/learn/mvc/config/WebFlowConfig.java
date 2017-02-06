
package com.djs.learn.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration
{
	private static final Logger logger = Logger.getLogger(WebFlowConfig.class);

	@Bean
	public FlowDefinitionRegistry flowRegistry(){
		logger.info("[flowRegistry]");

		return getFlowDefinitionRegistryBuilder().setBasePath("/WEB-INF/flows").addFlowLocationPattern("/**/*-flow.xml").build();

	}

	@Bean
	public FlowExecutor flowExecutor(){
		logger.info("[flowExecutor]");

		return getFlowExecutorBuilder(flowRegistry()).build();
	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping(){
		logger.info("[flowHandlerMapping]");

		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(flowRegistry());
		return handlerMapping;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter(){
		logger.info("[flowHandlerAdapter]");

		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}
}
