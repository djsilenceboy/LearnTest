set JAVA_HOME=C:\bea\ocsg41\jdk160_05
set PATH=%JAVA_HOME%\bin;%PATH%

set LIBPATH_T=..\temp\lib_jaxb_spring_ws
set LIBPATH_JU=..\target\test-classes

set LIBCLASSPATH=.;%LIBPATH_JU%;%LIBPATH_T%\junit-4.10.jar;%LIBPATH_T%\aopalliance-1.0.jar;%LIBPATH_T%\commons-codec-1.5.jar;%LIBPATH_T%\commons-httpclient-3.1.jar;%LIBPATH_T%\commons-logging-1.1.1.jar;%LIBPATH_T%\log4j-1.2.15.jar;%LIBPATH_T%\spring-asm-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-beans-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-context-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-core-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-expression-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-oxm-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-webmvc-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-ws-core-2.0.2.RELEASE.jar;%LIBPATH_T%\spring-xml-2.0.2.RELEASE.jar

set APP=..\target\maven_sample-0.0.1-SNAPSHOT.jar
set MAIN_CLASS=org.junit.runner.JUnitCore  com.djs.test.maven_sample.AppTest
