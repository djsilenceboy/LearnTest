set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_73
set PATH=%JAVA_HOME%\bin;%PATH%

set LIBPATH_T=..\target\dependency

set LIBCLASSPATH=.;%LIBPATH_T%\aopalliance-repackaged-2.5.0-b60.jar;%LIBPATH_T%\cdi-api-2.0.jar;%LIBPATH_T%\hk2-api-2.5.0-b60.jar;%LIBPATH_T%\hk2-locator-2.5.0-b60.jar;%LIBPATH_T%\hk2-utils-2.5.0-b60.jar;%LIBPATH_T%\javassist-3.22.0-CR2.jar;%LIBPATH_T%\javax.annotation-api-1.2.jar;%LIBPATH_T%\javax.el-api-3.0.0.jar;%LIBPATH_T%\javax.inject-1.jar;%LIBPATH_T%\javax.inject-2.5.0-b60.jar;%LIBPATH_T%\javax.interceptor-api-1.2.jar;%LIBPATH_T%\javax.json-1.1.jar;%LIBPATH_T%\javax.json-api-1.1.jar;%LIBPATH_T%\javax.json.bind-api-1.0.jar;%LIBPATH_T%\javax.ws.rs-api-2.1.jar;%LIBPATH_T%\jersey-client-2.26.jar;%LIBPATH_T%\jersey-common-2.26.jar;%LIBPATH_T%\jersey-hk2-2.26.jar;%LIBPATH_T%\jersey-media-jaxb-2.26.jar;%LIBPATH_T%\jersey-media-json-binding-2.26.jar;%LIBPATH_T%\log4j-1.2.17.jar;%LIBPATH_T%\osgi-resource-locator-1.0.1.jar;%LIBPATH_T%\yasson-1.0.jar

set APP=..\target\WsMavenSample2-1.0.0.jar
set MAIN_CLASS=com.djs.learn.TestMain
