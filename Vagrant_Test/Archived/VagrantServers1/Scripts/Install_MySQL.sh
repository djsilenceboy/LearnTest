#!/bin/bash

echo "Install MySQL."

# Install Repo and DB.
cd /vagrant
yum localinstall -y mysql57-community-release-el7-9.noarch.rpm
yum install -y mysql-community-server

# Enable and start service.
systemctl enable mysqld
systemctl start mysqld
sleep 5

# The scripts must run after vagrant up.

cat > /tmp/MySQL_Config_1.sh << EOF
#!/bin/bash

Var=\`grep 'temporary password' /var/log/mysqld.log\`
InitPassword=\${Var##* }
echo "Init Password = "\$InitPassword

# User / Password: root / P@ssw0rd
# Database: mysql
echo "Change initial password."
echo "Run: mysql -u root -p"
echo "Then run: ALTER USER root@localhost IDENTIFIED BY \"P@ssw0rd\";"
EOF

cat > /tmp/MySQL_Config_2.sh << EOF
#!/bin/bash

NewPassword=P@ssw0rd
echo "Prepare root user."

mysql -u root -p\$NewPassword -e 'CREATE USER root@'"'"'%'"'"' IDENTIFIED BY "'\$NewPassword'";'
mysql -u root -p\$NewPassword -e 'GRANT ALL ON *.* TO root@localhost WITH GRANT OPTION;'
mysql -u root -p\$NewPassword -e 'GRANT ALL ON *.* TO root@'"'"'%'"'"' WITH GRANT OPTION;'

echo "Restart service."
sudo systemctl restart mysqld
sleep 5

# User / Password: tester / P@ssw0rd
# Database: test
TestDb=test
TestUser=tester
TestPassword=P@ssw0rd
echo "Test Password = "\$TestPassword
echo "Prepare test DB and tester user."

mysql -u root -p\$NewPassword -e 'CREATE DATABASE '\$TestDb';'
mysql -u root -p\$NewPassword -e 'CREATE USER '\$TestUser'@localhost IDENTIFIED BY "'\$TestPassword'";'
mysql -u root -p\$NewPassword -e 'CREATE USER '\$TestUser'@'"'"'%'"'"' IDENTIFIED BY "'\$TestPassword'";'
mysql -u root -p\$NewPassword -e 'GRANT ALL ON '\$TestDb'.* TO '\$TestUser'@localhost WITH GRANT OPTION;'
mysql -u root -p\$NewPassword -e 'GRANT ALL ON '\$TestDb'.* TO '\$TestUser'@'"'"'%'"'"' WITH GRANT OPTION;'
EOF

chmod +x /tmp/MySQL_Config_*.sh

echo "Please run \"/tmp/MySQL_Config_1.sh\" and then \"/tmp/MySQL_Config_2.sh\"."
