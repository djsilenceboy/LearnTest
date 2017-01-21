
package com.djs.learn.mvc.config;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.djs.learn.mvc.domain.Product;

@Configuration
@EnableWebMvc
@ComponentScan("com.djs.learn.mvc")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter
{
	private static final Logger logger = Logger.getLogger(WebApplicationContextConfig.class);

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		logger.info(this.getClass().getName() + ":configureDefaultServletHandling");
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

	// Load text resource files from "\WEB-INF\classes".
	// The text resource files are packed from "/src/main/resources" folder of project.

	@Bean
	public MessageSource messageSource(){
		logger.info(this.getClass().getName() + ":messageSource");
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	// Load resource files from "/resources", and remap the path from "/resources/images/" to "/img/".
	// The resource files are packed from "/src/main/webapp/resources" folder of project.
	// Test: http://localhost:8080/SprSample4/img/P1234.png

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	// Test: http://localhost:8080/SprSample4/market/product.json?id=P1234

	@Bean
	public MappingJackson2JsonView jsonView(){
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);

		return jsonView;
	}

	// Test: http://localhost:8080/SprSample4/market/product.xml?id=P1234

	@Bean
	public MarshallingView xmlView(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Product.class);

		MarshallingView xmlView = new MarshallingView(marshaller);
		return xmlView;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		ArrayList<View> views = new ArrayList<>();
		views.add(jsonView());
		views.add(xmlView());

		resolver.setDefaultViews(views);

		return resolver;
	}
}
