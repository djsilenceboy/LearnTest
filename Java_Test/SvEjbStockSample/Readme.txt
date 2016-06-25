1. Copy "JBOSS5>/client/*.jar" to "./WebContent/WEB-INF/lib", and export jar, and deploy to Tomcat.

or

2. Modify following line in "<CATALINA>/conf/catalina.properties", add JBoss 5 client lib path.
     common.loader=${catalina.home}/lib,${catalina.home}/lib/*.jar,D:/WorkDJS/JBoss/jboss-5.1.0.GA/client/*.jar
