================================================================================
Use "jaxrs-ri-2.26.zip" for "WEB-INF/lib".
------------------------------------------------------------
Deploy to Tomcat in Eclipse.
------------------------------------------------------------
Test URL.

GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/{Number}

GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/q?Numbers=1&Numbers=1&Numbers=2&Numbers=3&Numbers=5&Numbers=8&Numbers=13&Numbers=21

POST: http://localhost:8080/WsTomcatSample3/SortFibonacciList/f with Number={Number}
------------------------------------------------------------
Depend on URL.

GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/{Number}
------------------------------------------------------------
Tested with SoapUI and browser.
================================================================================
