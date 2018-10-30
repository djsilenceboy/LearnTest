#!/bin/bash

Var=`grep 'temporary password' /var/log/mysqld.log`
InitPassword=${Var##* }
echo "Init Password = "$InitPassword

# User / Password: root / P@ssw0rd
# Database: mysql
echo "Change initial password."
echo "Login mysql console:"
echo "$ mysql -u root -p"
echo "Then change to new password:"
echo "> ALTER USER root@localhost IDENTIFIED BY \"P@ssw0rd\";"
