#!/bin/bash

echo "Install Apache httpd."

# Install.
yum install -y httpd

# Config httpd.
sed -i "s/\(ServerName\).*$/\1  "$(hostname)"/g" /etc/httpd/conf/httpd.conf

# Enable and start service.
systemctl enable httpd
systemctl start httpd
