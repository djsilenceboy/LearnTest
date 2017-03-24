#!/bin/bash

NewPassword=P@ssw0rd
echo "Prepare root user."

mysql -u root -p$NewPassword -e 'CREATE USER root@'"'"'%'"'"' IDENTIFIED BY "'$NewPassword'";'
mysql -u root -p$NewPassword -e 'GRANT ALL ON *.* TO root@localhost WITH GRANT OPTION;'
mysql -u root -p$NewPassword -e 'GRANT ALL ON *.* TO root@'"'"'%'"'"' WITH GRANT OPTION;'

echo "Restart service."
sudo systemctl restart mysqld
sleep 5

# User / Password: tester / P@ssw0rd
# Database: test
TestDb=test
TestUser=tester
TestPassword=P@ssw0rd
echo "Test Password = "$TestPassword
echo "Prepare test DB and tester user."

mysql -u root -p$NewPassword -e 'CREATE DATABASE '$TestDb';'
mysql -u root -p$NewPassword -e 'CREATE USER '$TestUser'@localhost IDENTIFIED BY "'$TestPassword'";'
mysql -u root -p$NewPassword -e 'CREATE USER '$TestUser'@'"'"'%'"'"' IDENTIFIED BY "'$TestPassword'";'
mysql -u root -p$NewPassword -e 'GRANT ALL ON '$TestDb'.* TO '$TestUser'@localhost WITH GRANT OPTION;'
mysql -u root -p$NewPassword -e 'GRANT ALL ON '$TestDb'.* TO '$TestUser'@'"'"'%'"'"' WITH GRANT OPTION;'
