#!/bin/bash

echo "Install Tomcat v9.0."

# Prepare Linux group and user.
groupadd tomcat
useradd -M -s /bin/nologin -g tomcat -d /opt/tomcat tomcat
usermod -a -G tomcat root

# Download file.
cd /tmp
curl -L -o apache-tomcat-9.0.43.zip https://www-us.apache.org/dist/tomcat/tomcat-9/v9.0.43/bin/apache-tomcat-9.0.43.zip

# Unzip/install Tomcat.
unzip /tmp/apache-tomcat-9.0.43.zip -d /opt
cd /opt
mv apache-tomcat-9.0.43 tomcat

# Change main folder privilege.
cd /opt
chgrp -R tomcat tomcat
chown -R tomcat tomcat
chmod -R ug+rwx tomcat
chmod -R o+rx tomcat

# Some useful links.
ln -s /opt/tomcat/conf /etc/tomcat
ln -s /opt/tomcat/logs /var/log/tomcat

# Enable default apps
mv /opt/tomcat/webapps.dist/* /opt/tomcat/webapps/

# Add admin user.
# User / Password: admin / P@ssw0rd
sed -i '/<\/tomcat-users>/ i\  <user username="admin" password="P@ssw0rd" roles="manager-gui,manager-status,manager-script"/>' /etc/tomcat/tomcat-users.xml
# Enable remote access console (from 192.168.0.0/16).
sed -i 's/\(^.*0:1\)\(.*$\)/\1|192\\.168\\.\\d+\\.\\d+\2/g' /opt/tomcat/webapps/manager/META-INF/context.xml

# Create Linux service.
cat > /etc/systemd/system/tomcat.service << EOF
# Systemd unit file for tomcat
[Unit]
Description=Apache Tomcat Web Application Container
After=syslog.target network.target

[Service]
Type=forking

Environment='JAVA_HOME=/usr/lib/jvm/jre'
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/urandom'

Environment='CATALINA_PID=/opt/tomcat/temp/tomcat.pid'
Environment='CATALINA_HOME=/opt/tomcat/'
Environment='CATALINA_BASE=/opt/tomcat/'
Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

User=tomcat
Group=tomcat

[Install]
WantedBy=multi-user.target
EOF

# Enable and start service.
systemctl daemon-reload
systemctl enable tomcat
systemctl start tomcat
