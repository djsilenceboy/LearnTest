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

1. Use DHCP gateway.
  config.vm.network "public_network", use_dhcp_assigned_default_route: true

2. But not use DHCP IP, will configure IP manually.
  config.vm.network "public_network", auto_config: false

3. Map a local folder as default "/vagrant" folder. Please change it accordingly.
  config.vm.synced_folder "F:/Download/Shared", "/vagrant", owner: "root", group: "root"

4. Install software.
  config.vm.provision "shell", path: "Scripts/Prepare_Yum.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_Python.sh"
  config.vm.provision "shell", path: "Scripts/Prepare_System.sh"

5. Also enable SSH password login.
  config.vm.provision "shell", inline: <<-END
    sudo sed -i "/^PasswordAuthentication.*$/d" /etc/ssh/sshd_config
    sudo sed -i "/^#PasswordAuthentication.*$/a PasswordAuthentication yes" /etc/ssh/sshd_config
    sudo systemctl restart sshd
  END

6. To determine the default network interface,
a) not add that "ifconfig" line first;
b) start VM and "vagrant ssh" login;
c) use "ip a" to find the default network interface, such as "eth1" or "enp0s8".

7. Configure IP manually. Please change network interface, IP and netmask accordingly.
    ansibleCentOS.vm.provision "shell", run: "always", inline: "ifconfig eth1 192.168.0.41 netmask 255.255.255.0 up"
================================================================================
