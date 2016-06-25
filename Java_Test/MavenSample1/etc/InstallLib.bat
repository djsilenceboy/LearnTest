:: It appears that install:install-file ignores the localRepositoryPath when using the version 2.2 of the plugin. However, it works with version 2.3 and later of the plugin. So use the fully qualified name of the plugin to specify the version.
:: mvn -e install:install-file

mvn -e org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file ^
 -DgroupId=com.djs.test ^
 -DartifactId=test_lib ^
 -Dversion=1.0.3.2 ^
 -Dpackaging=jar ^
 -Dfile=test_lib.jar ^
 -DlocalRepositoryPath=../lib ^
 -DgeneratePom=true
