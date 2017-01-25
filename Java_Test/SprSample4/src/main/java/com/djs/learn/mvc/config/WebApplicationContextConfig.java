
package com.djs.learn.mvc.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.djs.learn.mvc.domain.Product;
import com.djs.learn.mvc.interceptor.ProcessingTimeLogInterceptor;
import com.djs.learn.mvc.interceptor.PromoCodeInterceptor;
import com.djs.learn.mvc.validator.ProductValidator;
import com.djs.learn.mvc.validator.UnitsInStockValidator;

@Configuration
@EnableWebMvc
@ComponentScan("com.djs.learn.mvc")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter
{
	private static final Logger logger = Logger.getLogger(WebApplicationContextConfig.class);

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		logger.info("[configureDefaultServletHandling]");

		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		logger.info("[getInternalResourceViewResolver]");

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
		logger.info("[messageSource]");
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	// Load resource files from "/resources", and remap the path from "/resources/images/" to "/img/".
	// The resource files are packed from "/src/main/webapp/resources" folder of project.
	// Test: http://localhost:8080/SprSample4/img/P1234.png

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		logger.info("[addResourceHandlers]");

		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver(){
		logger.info("[multipartResolver]");

		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	// Test: http://localhost:8080/SprSample4/market/product.json?id=P1234

	@Bean
	public MappingJackson2JsonView jsonView(){
		logger.info("[jsonView]");

		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);

		return jsonView;
	}

	// Test: http://localhost:8080/SprSample4/market/product.xml?id=P1234

	@Bean
	public MarshallingView xmlView(){
		logger.info("[xmlView]");

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Product.class);

		MarshallingView xmlView = new MarshallingView(marshaller);
		return xmlView;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
		logger.info("[contentNegotiatingViewResolver]");

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		ArrayList<View> views = new ArrayList<>();
		views.add(jsonView());
		views.add(xmlView());

		resolver.setDefaultViews(views);

		return resolver;
	}

	// When LocaleChangeInterceptor invoked, return SessionLocaleResolver.
	// Test: http://localhost:8080/SprSample4/market/products/add

	@Bean
	public LocaleResolver localeResolver(){
		logger.info("[localeResolver]");

		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));

		return resolver;
	}

	// When promoCodeInterceptor invoked, it will check "promo=XXX".
	// Test: http://localhost:8080/SprSample4/market/products/specialOffer?promo=OFF3R
	// Test: http://localhost:8080/SprSample4/market/products/specialOffer?promo=Unknown

	@Bean
	public HandlerInterceptor promoCodeInterceptor(){
		logger.info("[promoCodeInterceptor]");

		PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
		promoCodeInterceptor.setPromoCode("OFF3R");
		promoCodeInterceptor.setOfferRedirect("market/products");
		promoCodeInterceptor.setErrorRedirect("invalidPromoCode");

		return promoCodeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		logger.info("[addInterceptors]");

		// Add interceptor 1.
		registry.addInterceptor(new ProcessingTimeLogInterceptor());

		// Add interceptor 2.
		// LocaleChangeInterceptor will load LocaleResolver.
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);

		// Add interceptor 3.
		// Add URL patterns to which the registered interceptor should apply to.
		registry.addInterceptor(promoCodeInterceptor()).addPathPatterns("/**/market/products/specialOffer");
	}

	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator(){
		logger.info("[validator]");

		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());

		return bean;
	}

	// Return default validator.
	@Override
	public Validator getValidator(){
		logger.info("[getValidator]");

		return validator();
	}

	// Test: http://localhost:8080/SprSample4/market/products/add

	@Bean
	public ProductValidator productValidator(){
		logger.info("[productValidator]");

		Set<Validator> springValidators = new HashSet<>();
		springValidators.add(new UnitsInStockValidator());

		ProductValidator productValidator = new ProductValidator();
		productValidator.setSpringValidators(springValidators);

		return productValidator;
	}
}
