================================================================================
ansible-playbook VagrantServers.yml -vvv

ansible-playbook -l VagrantDbServer VagrantServers.yml -vvv
ansible-playbook -l VagrantApplicationServer VagrantServers.yml -vvv
ansible-playbook -l VagrantJbossServer VagrantServers.yml -vvv
ansible-playbook -l VagrantDockerServer VagrantServers.yml -vvv
------------------------------------------------------------
ansible-playbook VagrantDbServer.yml -vvv
ansible-playbook VagrantApplicationServer.yml -vvv
ansible-playbook VagrantJbossServer.yml -vvv
ansible-playbook VagrantDockerServer.yml -vvv
------------------------------------------------------------
ansible-playbook VagrantDbServer_PostgreSQL.yml -vvv
ansible-playbook VagrantDbServer_MySQL.yml -vvv
ansible-playbook VagrantDbServer_MongoDB.yml -vvv
------------------------------------------------------------
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
