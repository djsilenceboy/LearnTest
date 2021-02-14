#!/bin/bash

echo "Install MongoDB v4.0."

# Add Repo.
cat > /etc/yum.repos.d/mongodb-org-4.4.repo << EOF
[mongodb-org-4.4]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/4.4/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-4.4.asc
EOF

# Install.
dnf install -y mongodb-org

# Enable remote access.
sed -i "s/127\.0\.0\.1/0\.0\.0\.0/g" /etc/mongod.conf

# Enable and start service.
systemctl enable mongod
systemctl start mongod
sleep 5

# Create users for admin DB and test DB.
mongo << EOF
use admin
db.createUser(
  {
    user: "root",
    pwd: "P@ssw0rd",
    roles: [{role: "root", db: "admin"}]
  }
)
db.createUser(
  {
    user: "admin",
    pwd: "P@ssw0rd",
    roles: [{role: "userAdminAnyDatabase", db: "admin"}]
  }
)

use test
db.createUser(
  {
    user: "tester",
    pwd: "P@ssw0rd",
    roles: [{role: "dbOwner", db: "test"}]
 }
)
EOF
