#!/bin/bash

echo "Install PostgreSQL."

# Add Repo.
yum install -y http://yum.postgresql.org/9.5/redhat/rhel-7-x86_64/pgdg-centos95-9.5-3.noarch.rpm

# Install.
yum install -y postgresql95-server postgresql95-contrib
# or
# yum groupinstall -y "PostgreSQL Database Server 9.5 PGDG"

# Initial DB.
/usr/pgsql-9.5/bin/postgresql95-setup initdb

# Config DB. Enable remote access.
sed -i '/.*listen_addresses.*/ a\listen_addresses = '"'"'*'"'" /var/lib/pgsql/9.5/data/postgresql.conf
sed -i 's/^local.*$/local   all             all                                     trust/g' /var/lib/pgsql/9.5/data/pg_hba.conf
sed -i '$ a\host    all             all             192.168.0.1/24          md5' /var/lib/pgsql/9.5/data/pg_hba.conf

# Enable and start service.
systemctl enable postgresql-9.5
systemctl start postgresql-9.5
sleep 5

# Add user, create DB and grant privileges.
# User / Password: tester / P@ssw0rd
# Database: test
su - postgres -c "psql -c \"CREATE ROLE tester WITH LOGIN CREATEDB PASSWORD 'P@ssw0rd'\""
su - postgres -c "psql -c \"CREATE DATABASE test OWNER tester\""
su - postgres -c "psql -c \"GRANT ALL PRIVILEGES ON DATABASE test TO tester\""
