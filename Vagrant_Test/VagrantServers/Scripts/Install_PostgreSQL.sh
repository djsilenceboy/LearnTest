#!/bin/bash

echo "Install PostgreSQL v9.6."

# Add Repo.
dnf install -y http://yum.postgresql.org/9.6/redhat/rhel-7-x86_64/pgdg-centos96-9.6-3.noarch.rpm

# Install.
dnf install -y postgresql96 postgresql96-server postgresql96-contrib
# or
# dnf group install -y "PostgreSQL Database Server 9.6 PGDG"

# Initial DB.
/usr/pgsql-9.6/bin/postgresql96-setup initdb

# Config DB. Enable remote access.
sed -i '/.*listen_addresses.*/ a\listen_addresses = '"'"'*'"'" /var/lib/pgsql/9.6/data/postgresql.conf
sed -i 's/^local.*$/local   all             all                                     trust/g' /var/lib/pgsql/9.6/data/pg_hba.conf
sed -i '$ a\host    all             all             192.168.0.1/16          md5' /var/lib/pgsql/9.6/data/pg_hba.conf

# Enable and start service.
systemctl enable postgresql-9.6
systemctl start postgresql-9.6
sleep 5

# Add user, create DB and grant privileges.
# User / Password: tester / P@ssw0rd
# Database: test
su - postgres -c "psql -c \"CREATE ROLE tester WITH LOGIN CREATEDB PASSWORD 'P@ssw0rd'\""
su - postgres -c "psql -c \"CREATE DATABASE test OWNER tester\""
su - postgres -c "psql -c \"GRANT ALL PRIVILEGES ON DATABASE test TO tester\""
