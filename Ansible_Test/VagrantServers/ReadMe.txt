================================================================================
Setup for CentOS 7
================================================================================
Step 1: General installations for all servers.
------------------------------------------------------------
Process for all servers.

ansible-playbook VagrantServers.yml -vvv
------------------------------------------------------------
Process for each server.

ansible-playbook -l VagrantDbServer VagrantServers.yml -vvv
ansible-playbook -l VagrantApplicationServer VagrantServers.yml -vvv
ansible-playbook -l VagrantJbossServer VagrantServers.yml -vvv
ansible-playbook -l VagrantWebServer VagrantServers.yml -vvv
ansible-playbook -l VagrantDockerServer VagrantServers.yml -vvv
ansible-playbook -l VagrantDockerServer2 VagrantServers.yml -vvv
================================================================================
Step 2: Special installations for each server.
------------------------------------------------------------
Process for each server.

ansible-playbook VagrantDbServer.yml -vvv
ansible-playbook VagrantApplicationServer.yml -vvv
ansible-playbook VagrantJbossServer.yml -vvv
ansible-playbook VagrantWebServer.yml -vvv
ansible-playbook -l VagrantDockerServer VagrantDockerServer.yml -vvv
ansible-playbook -l VagrantDockerServer2 VagrantDockerServer.yml -vvv
================================================================================
Step 2: Special installations for each server. (For debug purpose)
------------------------------------------------------------
Detailed process for DB server.

ansible-playbook VagrantDbServer_PostgreSQL.yml -vvv
ansible-playbook VagrantDbServer_MySQL.yml -vvv
ansible-playbook VagrantDbServer_MongoDB.yml -vvv
------------------------------------------------------------
Detailed process for Application server.

ansible-playbook VagrantApplicationServer_Development.yml -vvv
ansible-playbook VagrantApplicationServer_Ansible.yml -vvv
ansible-playbook VagrantApplicationServer_httpd.yml -vvv
ansible-playbook VagrantApplicationServer_Tomcat.yml -vvv
ansible-playbook VagrantApplicationServer_Jenkins.yml -vvv
================================================================================
There is "files" for configuration and script files.

Check and update those files for such as IP, user name and password, etc.
================================================================================
In YAML file, there is a local folder "/cygdrive/f/Download/Shared", which stores required installation files.
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

(For JBoss EAP.)
JDBC_Drivers/mysql-connector-java-5.1.41-bin.jar
JDBC_Drivers/ojdbc6-11.2.0.4.jar
JDBC_Drivers/postgresql-42.0.0.jar
------------------------------------------------------------
If using different version of files, remember to update the YAML and config files.
================================================================================

================================================================================
Apache httpd v2.4.x (Linux)

Server: 192.168.0.43
Port: 80
Web console: http://192.168.0.43
================================================================================
Apache Tomcat v8.0.x (Linux)

Server: 192.168.0.43
HTTP/1.1 port: 8080
Web console: http://192.168.0.43:8080

Admin user / Password: admin / P@ssw0rd
Roles: admin-gui,manager-gui
================================================================================
Jenkins v2.32.x (Linux, for Tomcat)

Server: 192.168.0.43

Web console: http://192.168.0.43:8080/jenkins
================================================================================
JBoss Enterprise Application Platform v7.x.x (Linux)

Server: 192.168.0.44

Installation path: /opt/eap7
Admin user / Password: admin / P@ssw0rd

Port:
ajp / 8009
http / 8080
https / 8443
management-http / 9990
management-https / 9993
------------------------------------------------------------
Admin portal

http://192.168.0.44:9990/console
================================================================================
Nginx (Linux, Docker)

Server: 192.168.0.45
Port: 8000
Web console: http://192.168.0.45:8000
================================================================================

================================================================================
PostgreSQL v9.5 (Linux)

Server: 192.168.0.42
Port: 5432
User name / Password: postgres / <NotSet> (admin)
User name / Password: tester / P@ssw0rd
------------------------------------------------------------
Database: test
JDBC: jdbc:postgresql://192.168.0.42:5432/test
================================================================================
MySQL v5.7 (Linux)

Server: 192.168.0.42
Port: 3306
User name / Password: root / P@ssw0rd (admin)
User name / Password: tester / P@ssw0rd
------------------------------------------------------------
Schema/Database: test
JDBC: jdbc:mysql://192.168.0.42:3306/test
================================================================================
MongoDB v3.4 (Linux)

Server: 192.168.0.42
Port: 27017
------------------------------------------------------------
DB: admin

User name / Password: root / P@ssw0rd
Roles: [{role: "root", db: "admin"}]

User name / Password: admin / P@ssw0rd
Roles: [{role: "userAdminAnyDatabase", db: "admin"}]
------------------------------------------------------------
DB: test

User name / Password: tester / P@ssw0rd
Roles: [{role: "dbOwner", db: "test"}]
================================================================================
