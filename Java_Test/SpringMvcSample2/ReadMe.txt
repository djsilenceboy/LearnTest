================================================================================
Use Spring MVC for service code.

Sample from:
http://www.codejava.net/frameworks/spring/understanding-spring-mvc?showall=&start=7
with modification.
------------------------------------------------------------
Deploy to Tomcat in Eclipse.
------------------------------------------------------------
Test URL.

Page: http://localhost:8080/SpringMvcSample2
------------------------------------------------------------
Tested with browser.
================================================================================
"maven-war-plugin" for VirturlBox
------------------------------------------------------------
When running this project from a shared disk in VirtualBox, it must use version 2.4 for WAR plugin.

				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.4</version>

Otherwise, it can use higher version, such as "2.6", "3.2.0".
================================================================================
