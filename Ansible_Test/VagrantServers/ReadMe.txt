================================================================================
Setup for CentOS 7
================================================================================
Installations for each server.
------------------------------------------------------------
ansible-playbook -l VagrantCentOS VagrantCentOS.yml -vvv

ansible-playbook -l VagrantDbServer VagrantDbServer.yml -vvv
ansible-playbook -l VagrantApplicationServer VagrantApplicationServer.yml -vvv
ansible-playbook -l VagrantJbossServer VagrantJbossServer.yml -vvv

ansible-playbook -l VagrantDockerDbServer VagrantDockerDbServer.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer VagrantDockerApplicationServer.yml -vvv

ansible-playbook -l VagrantDockerServer VagrantDockerServer.yml -vvv
ansible-playbook -l VagrantDockerServer2 VagrantDockerServer2.yml -vvv
================================================================================
Step by step installations for each server. (For debugging)
------------------------------------------------------------
VagrantCentOS

ansible-playbook -l VagrantCentOS Playbooks/Setup_CentOS.yml -vvv
ansible-playbook -l VagrantCentOS Playbooks/Install_Development.yml -vvv
ansible-playbook -l VagrantCentOS Playbooks/Install_Desktop.yml -vvv
------------------------------------------------------------
VagrantDbServer

ansible-playbook -l VagrantDbServer Playbooks/Setup_CentOS.yml -vvv
ansible-playbook -l VagrantDbServer Playbooks/Install_Development.yml -vvv
ansible-playbook -l VagrantDbServer Playbooks/Install_PostgreSQL.yml -vvv
ansible-playbook -l VagrantDbServer Playbooks/Install_MySQL.yml -vvv
ansible-playbook -l VagrantDbServer Playbooks/Install_MongoDB.yml -vvv
------------------------------------------------------------
VagrantApplicationServer

ansible-playbook -l VagrantApplicationServer Playbooks/Setup_CentOS.yml -vvv
ansible-playbook -l VagrantApplicationServer Playbooks/Install_Development.yml -vvv
ansible-playbook -l VagrantApplicationServer Playbooks/Install_Ansible.yml -vvv
ansible-playbook -l VagrantApplicationServer Playbooks/Install_httpd.yml -vvv
ansible-playbook -l VagrantApplicationServer Playbooks/Install_Tomcat.yml -vvv
ansible-playbook -l VagrantApplicationServer Playbooks/Install_Tomcat_Jenkins.yml -vvv
------------------------------------------------------------
VagrantJbossServer

ansible-playbook -l VagrantJbossServer Playbooks/Setup_CentOS.yml -vvv
ansible-playbook -l VagrantJbossServer Playbooks/Install_Development.yml -vvv
ansible-playbook -l VagrantJbossServer Playbooks/Install_JbossEAP.yml -vvv
------------------------------------------------------------
VagrantDockerDbServer

ansible-playbook -l VagrantDockerDbServer VagrantDockerServerBasic.yml -vvv
ansible-playbook -l VagrantDockerDbServer Playbooks/Install_Docker_PostgreSQL.yml -vvv
ansible-playbook -l VagrantDockerDbServer Playbooks/Install_Docker_MySQL.yml -vvv
ansible-playbook -l VagrantDockerDbServer Playbooks/Install_Docker_MongoDB.yml -vvv
------------------------------------------------------------
VagrantDockerApplicationServer

ansible-playbook -l VagrantDockerApplicationServer VagrantDockerServerBasic.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer Playbooks/Install_Docker_Nginx.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer Playbooks/Install_Docker_Tomcat.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer Playbooks/Install_Docker_Jenkins.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer Playbooks/Install_Docker_TeamCity.yml -vvv
ansible-playbook -l VagrantDockerApplicationServer Playbooks/Install_Docker_JBoss_Wildfly.yml -vvv
------------------------------------------------------------
VagrantDockerServer

ansible-playbook -l VagrantDockerServer VagrantDockerServerBasic.yml -vvv
ansible-playbook -l VagrantDockerServer Playbooks/Enable_Docker_RemoteAccess.yml -vvv
ansible-playbook -l VagrantDockerServer Playbooks/Install_Docker_Registry.yml -vvv
------------------------------------------------------------
VagrantDockerServer2

ansible-playbook -l VagrantDockerServer2 VagrantDockerServerBasic.yml -vvv
ansible-playbook -l VagrantDockerServer2 Playbooks/Enable_Docker_RemoteAccess.yml -vvv
================================================================================
There is "files" for configuration and script files.

Check and update those files for such as IP, user name and password, etc.
================================================================================
For VagrantJbossServer.

1. Download file (need login).
https://developers.redhat.com/download-manager/file/jboss-eap-7.1.0-installer.jar

2. Upload to "/tmp" on VM.

3. Run playbooks.
================================================================================
For VagrantDockerServer2
------------------------------------------------------------
From VagrantDockerServer, get cert.

]$ cat /opt/docker/registry/certs/docker_registry.crt
------------------------------------------------------------
On VagrantDockerServer2.

Add VagrantDockerServer host.

]$ sudo vi /etc/hosts
192.168.10.16    docker.djsilenceboy.com
----------------------------------------
Create cert file.

]$ sudo mkdir -p /etc/docker/certs.d/docker.djsilenceboy.com:5000
]$ sudo vi /etc/docker/certs.d/docker.djsilenceboy.com:5000/ca.crt
(Paste cert of "docker_registry.crt")
================================================================================

================================================================================
PostgreSQL v9.6 (Linux)

Server: 192.168.10.11
Port: 5432
User name / Password: postgres / <NotSet> (admin)
User name / Password: tester / P@ssw0rd
------------------------------------------------------------
Database: test
JDBC: jdbc:postgresql://192.168.10.11:5432/test
================================================================================
MySQL v5.7 (Linux)

Server: 192.168.10.11
Port: 3306
User name / Password: root / P@ssw0rd (admin)
User name / Password: tester / P@ssw0rd
------------------------------------------------------------
Schema/Database: test
JDBC: jdbc:mysql://192.168.10.11:3306/test
================================================================================
MongoDB v4.0 (Linux)

Server: 192.168.10.11
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

================================================================================
Apache httpd v2.4.x (Linux)

Server: 192.168.10.12
Port: 80
Web console: http://192.168.10.12
================================================================================
Apache Tomcat v9.0.x (Linux)

Server: 192.168.10.12
HTTP/1.1 port: 8080
Web console: http://192.168.10.12:8080

Admin user / Password: admin / P@ssw0rd
Roles: admin-gui,manager-gui
================================================================================
Jenkins v2.x.x (Linux, for Tomcat)

Server: 192.168.10.12

Web console: http://192.168.10.12:8080/jenkins
================================================================================
JBoss Enterprise Application Platform v7.x.x (Linux)

Server: 192.168.10.13

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

http://192.168.10.13:9990/console
================================================================================

================================================================================
PostgreSQL v9.x (Linux, Docker)

Server: 192.168.10.14
Port: 5432
User name / Password: tester / P@ssw0rd (admin)
------------------------------------------------------------
Database: test
JDBC: jdbc:postgresql://192.168.10.14:5432/test
================================================================================
MySQL v5.7 (Linux, Docker)

Server: 192.168.10.14
Port: 3306
User name / Password: root / P@ssw0rd (admin)
User name / Password: tester / P@ssw0rd
------------------------------------------------------------
Schema/Database: test
JDBC: jdbc:mysql://192.168.10.14:3306/test
================================================================================
MongoDB (Linux, Docker)

Server: 192.168.10.14
Port: 27017
------------------------------------------------------------
DB: admin

User name / Password: root / P@ssw0rd
Roles: [{role: "root", db: "admin"}]
------------------------------------------------------------
Admin portal

http://192.168.10.14:8081/
================================================================================

================================================================================
Nginx (Linux, Docker)

Server: 192.168.10.15
Port: 8070
Web console: http://192.168.10.15:8070
================================================================================
Apache Tomcat v9.0.x (Linux, Docker)

Server: 192.168.10.15
Port: 8080
Port: 8082
Web console: http://192.168.10.15:8080
             http://192.168.10.15:8082

Admin user / Password: admin / P@ssw0rd
Roles: admin-gui,manager-gui
================================================================================
Jenkins v2.x.x (Linux, Docker)

Server: 192.168.10.15

Web console: http://192.168.10.15:8090/jenkins
================================================================================
TeamCity (Linux, Docker)

Server: 192.168.10.15

Web console: http://192.168.10.15:8111/

Admin user / Password: admin / P@ssw0rd
================================================================================
JBoss Wildfly (Linux, Docker)

Server: 192.168.10.15

Admin user / Password: admin / P@ssw0rd

Port:
http / 8100
management-http / 9990
------------------------------------------------------------
Admin portal

http://192.168.10.15:9990/console
================================================================================
