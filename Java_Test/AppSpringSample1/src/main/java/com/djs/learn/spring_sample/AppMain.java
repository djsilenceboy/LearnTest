
package com.djs.learn.spring_sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.djs.learn.spring_sample.greeting.GreetingService;
import com.djs.learn.spring_sample.knightI.Knight;

public class AppMain
{
	public static void main(String[] args) throws Exception{
		// Notes: AOP doesn't work while using XmlBeanFactory!
		// BeanFactory factory = new XmlBeanFactory( new FileSystemResource( "application-context.xml" ) );
		// BeanFactory factory = new XmlBeanFactory( new ClassPathResource( "application-context.xml" ) );

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
		        new String[]{"spring_config/sample/greeting.xml", "spring_config/sample/knight.xml", "spring_config/sample/music.xml"});

		GreetingService greetingService = (GreetingService)appContext.getBean("greetingService");
		greetingService.sayGreeting();

		Knight knightA = (Knight)appContext.getBean("knightA");
		knightA.embarkOnQuest();

		Knight knightB = (Knight)appContext.getBean("knightB");
		knightB.embarkOnQuest();
	}
}
