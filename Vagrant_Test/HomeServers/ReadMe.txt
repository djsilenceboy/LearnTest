================================================================================
In Vagrantfile, there is a synced_folder "F:/Download/Shared", which stores required installation files and configuration files.
Please change it to your valid local folder, and prepare following files inside:
------------------------------------------------------------
(For Apache Tomcat.)
apache-tomcat-8.0.39.zip
catalina-jmx-remote.jar
catalina-ws.jar

(For Jenkins deployed on Tomcat.)
jenkins.war

(For MySQL.)
mysql57-community-release-el7-9.noarch.rpm

(For JBoss EAP.)
jboss-eap-7.0.0-installer.jar
auto.xml
auto.xml.variables

(For JBoss EAP.)
JDBC_Drivers/mysql-connector-java-5.1.41-bin.jar
JDBC_Drivers/ojdbc6-11.2.0.4.jar
JDBC_Drivers/postgresql-42.0.0.jar
------------------------------------------------------------
If using different version of files, remember to update the shell scripts.
================================================================================
