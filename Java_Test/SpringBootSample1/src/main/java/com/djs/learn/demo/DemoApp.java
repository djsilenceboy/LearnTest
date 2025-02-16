
package com.djs.learn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DemoApp
{
	public static void main(String[] args){
		SpringApplication.run(DemoApp.class, args);
	}
}
