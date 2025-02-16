
package com.djs.learn.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "greeting")
@Data
public class Greeting
{
	private String myName;
	private String friendName;
	private String message;
}
