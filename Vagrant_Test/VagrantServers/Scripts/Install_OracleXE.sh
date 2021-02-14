#!/bin/bash

echo "Install Oracle XE."

# Install.
cd /vagrant
dnf install -y oracle-xe-11.2.0-1.0.x86_64.rpm

# Config DB.
# User / Password: SYS / system
# User / Password: SYSTEM / system
echo "Password = system"
sudo /etc/init.d/oracle-xe configure responseFile=/vagrant/xe.rsp

echo "Go to its portal to continue configuration."
