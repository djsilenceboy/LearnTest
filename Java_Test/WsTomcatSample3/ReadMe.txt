================================================================================
Use JAX-RS Jersey for service code.
------------------------------------------------------------
Deploy to Tomcat in Eclipse.
------------------------------------------------------------
Depend on WsTomcatSample2.

GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/{Number}
------------------------------------------------------------
Test URL.

GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/{Number}

GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/q?Numbers=1&Numbers=1&Numbers=2&Numbers=3&Numbers=5&Numbers=8&Numbers=13&Numbers=21

POST: http://localhost:8080/WsTomcatSample3/SortFibonacciList/f with Number={Number}
------------------------------------------------------------
Tested with SoapUI and browser.
================================================================================
"maven-war-plugin" for VirturlBox
------------------------------------------------------------
When running this project from a shared disk in VirtualBox, it must use version 2.4 for WAR plugin.

				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.4</version>

Otherwise, it can use higher version, such as "2.6", "3.2.0".
================================================================================
