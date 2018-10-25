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
This config file:

1.
  # Adapter 2: Use private network Host-Only with fixed IP in same subnet.
  # Netmask 192.168.10.0/24
  # Host can access them.
  # They can access each other, host and Internet.

2. Map a local folder as default "/vagrant" folder. Please change it accordingly.
  config.vm.synced_folder "F:/Download/Shared", "/vagrant", owner: "root", group: "root"

3. Install software.
  config.vm.provision "shell", path: "Scripts/Prepare_Yum.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_Python.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_System.sh"

4. Also enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END
================================================================================
