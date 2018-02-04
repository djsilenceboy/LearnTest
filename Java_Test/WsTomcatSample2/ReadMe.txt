================================================================================
Use JAX-RS Jersey for service code.
------------------------------------------------------------
Deploy to Tomcat in Eclipse.
------------------------------------------------------------
Test URL.

GET: http://localhost:8080/WsTomcatSample2/Hello

GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/u/{Number}

POST: http://localhost:8080/WsTomcatSample2/GetFibonacciList/u with Number={Number}

GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/{Number}
GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/h/{Number}

GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/q?Number={Number}
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
