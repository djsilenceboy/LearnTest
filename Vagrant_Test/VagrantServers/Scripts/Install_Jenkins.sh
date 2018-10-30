#!/bin/bash

echo "Install Jenkins for Tomcat."

# Prepare main folder.
mkdir -p /opt/jenkins
cd /opt
chgrp tomcat jenkins
chown tomcat jenkins
chmod ug+rwx jenkins
chmod o+rx jenkins

# Add config in Tomcat service.
sed -i '/.*JAVA_OPTS.*/ a\Environment=JENKINS_HOME=/opt/jenkins' /etc/systemd/system/tomcat.service

# Download file.
cd /opt/tomcat/webapps
curl -L -o jenkins.war http://mirrors.jenkins.io/war-stable/latest/jenkins.war

# Change privileges.
cd /opt/tomcat/webapps
chgrp tomcat jenkins.war
chown tomcat jenkins.war
chmod ug+rwx jenkins.war
chmod o+rx jenkins.war

# Restart Tomcat service.
systemctl daemon-reload
systemctl restart tomcat
