set JAVA_HOME=%ProgramFiles%\Java\jdk1.7.0_65
set MAVEN_HOME=%ProgramFiles(x86)%\Apache Software Foundation\maven-3.2.2

set PATH=%PATH%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin

:: Property file
:: Line format: groupId;artifactId;version;filePath
:: Separated by comma, no heading/tailling space allowed.
:: filePath can be absolute or relative.
:: Use "#" to remark a line.

:: It appears that install:install-file ignores the localRepositoryPath when using the version 2.2 of the plugin. However, it works with version 2.3 and later of the plugin. So use the fully qualified name of the plugin to specify the version.
:: mvn -e install:install-file

for /f "eol=# tokens=1,2,3,4 delims=;" %%a IN (local_maven_lib.properties) DO (
mvn -e org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file ^
 -DgroupId=%%a ^
 -DartifactId=%%b ^
 -Dversion=%%c ^
 -Dpackaging=jar ^
 -Dfile=%%d ^
 -DgeneratePom=true
)
