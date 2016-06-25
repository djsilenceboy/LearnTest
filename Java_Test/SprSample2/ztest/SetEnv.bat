set JAVA_HOME=C:\bea\ocsg41\jdk160_05
set PATH=%JAVA_HOME%\bin;%PATH%

set CONFIGPATH=..\etc\config
set LIBPATH_T=..\temp\lib
set LIBPATH_JU=..\target\test-classes

set LIBCLASSPATH=.;%CONFIGPATH%;%LIBPATH_JU%;%LIBPATH_T%\junit-4.10.jar;%LIBPATH_T%\antlr-2.7.7.jar;%LIBPATH_T%\aopalliance-1.0.jar;%LIBPATH_T%\aspectjweaver-1.6.11.jar;%LIBPATH_T%\commons-collections-3.2.jar;%LIBPATH_T%\commons-dbcp-1.4.jar;%LIBPATH_T%\commons-logging-1.1.1.jar;%LIBPATH_T%\commons-pool-1.5.4.jar;%LIBPATH_T%\dom4j-1.6.1.jar;%LIBPATH_T%\ehcache-core-2.4.5.jar;%LIBPATH_T%\ehcache-spring-annotations-1.2.0.jar;%LIBPATH_T%\guava-r09.jar;%LIBPATH_T%\hibernate3-3.6.8.Final.jar;%LIBPATH_T%\hibernate-jpa-2.0-api-1.0.1.Final.jar;%LIBPATH_T%\javassist-3.12.0.GA.jar;%LIBPATH_T%\jta-1.1.jar;%LIBPATH_T%\log4j-1.2.15.jar;%LIBPATH_T%\ojdbc-1.4-10.2.0.5.jar;%LIBPATH_T%\slf4j-api-1.6.3.jar;%LIBPATH_T%\slf4j-log4j12-1.6.3.jar;%LIBPATH_T%\spring-aop-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-asm-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-beans-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-context-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-context-support-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-core-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-expression-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-jdbc-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-orm-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-tx-3.0.6.RELEASE.jar

set APP=..\target\spring_sample-0.0.1-SNAPSHOT.jar
set MAIN_CLASS=org.junit.runner.JUnitCore  com.djs.test.spring_sample.AppTest
