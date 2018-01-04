set JAVA_HOME=C:\bea\ocsg41\jdk160_05
set PATH=%JAVA_HOME%\bin;%PATH%

set LIBPATH_T=..\temp\lib_cxf
set LIBPATH_JU=..\target\test-classes

set LIBCLASSPATH=.;%LIBPATH_JU%;%LIBPATH_T%\junit-4.10.jar;%LIBPATH_T%\aopalliance-1.0.jar;%LIBPATH_T%\commons-logging-1.1.1.jar;%LIBPATH_T%\cxf-api-2.4.4.jar;%LIBPATH_T%\cxf-common-utilities-2.4.4.jar;%LIBPATH_T%\cxf-rt-bindings-soap-2.4.4.jar;%LIBPATH_T%\cxf-rt-core-2.4.4.jar;%LIBPATH_T%\cxf-rt-databinding-jaxb-2.4.4.jar;%LIBPATH_T%\cxf-rt-frontend-jaxws-2.4.4.jar;%LIBPATH_T%\cxf-rt-frontend-simple-2.4.4.jar;%LIBPATH_T%\cxf-rt-transports-http-2.4.4.jar;%LIBPATH_T%\cxf-rt-ws-addr-2.4.4.jar;%LIBPATH_T%\cxf-tools-common-2.4.4.jar;%LIBPATH_T%\log4j-1.2.15.jar;%LIBPATH_T%\neethi-3.0.1.jar;%LIBPATH_T%\spring-aop-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-asm-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-beans-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-context-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-core-3.0.6.RELEASE.jar;%LIBPATH_T%\spring-expression-3.0.6.RELEASE.jar;%LIBPATH_T%\stax2-api-3.1.1.jar;%LIBPATH_T%\wsdl4j-1.6.2.jar;%LIBPATH_T%\xmlschema-core-2.0.1.jar

set APP=..\target\maven_sample-0.0.1-SNAPSHOT.jar
set MAIN_CLASS=org.junit.runner.JUnitCore  com.djs.test.maven_sample.AppTest
