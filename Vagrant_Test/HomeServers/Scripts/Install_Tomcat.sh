#!/bin/bash

echo "Install Tomcat."

# Prepare Linux group and user.
groupadd tomcat
useradd -M -s /bin/nologin -g tomcat -d /opt/tomcat tomcat
usermod -a -G tomcat root

# Unzip/install Tomcat.
cd /vagrant
unzip apache-tomcat-8.0.39.zip -d /opt
cd /opt
mv apache-tomcat-8.0.39 tomcat

# Some plugin/addon.
cd /vagrant
cp catalina-jmx-remote.jar catalina-ws.jar /opt/tomcat/lib

# Change main folder privilege.
cd /opt
chgrp -R tomcat tomcat
chown -R tomcat tomcat
chmod -R ug+rwx tomcat
chmod -R o+rx tomcat

# Some useful links.
ln -s /opt/tomcat/conf /etc/tomcat
ln -s /opt/tomcat/logs /var/log/tomcat

# Add admin user.
# User / Password: admin / P@ssw0rd
# <tomcat-users>
#  <user name="admin" password="P@ssw0rd" roles="admin-gui,manager-gui"/>
# </tomcat-users>
sed -i '/<\/tomcat-users>/ i\  <user name="admin" password="P@ssw0rd" roles="admin-gui,manager-gui"/>' /etc/tomcat/tomcat-users.xml

# Create Linux service.
cat > /etc/systemd/system/tomcat.service << EOF
# Systemd unit file for tomcat
[Unit]
Description=Apache Tomcat Web Application Container
After=syslog.target network.target

[Service]
Type=forking

Environment=JAVA_HOME=/usr/lib/jvm/jre
Environment=CATALINA_PID=/opt/tomcat/temp/tomcat.pid
Environment=CATALINA_HOME=/opt/tomcat/
Environment=CATALINA_BASE=/opt/tomcat/
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/bin/kill -15 $MAINPID

User=tomcat
Group=tomcat

[Install]
WantedBy=multi-user.target
EOF

# Enable and start service.
systemctl daemon-reload
systemctl enable tomcat
systemctl start tomcat
