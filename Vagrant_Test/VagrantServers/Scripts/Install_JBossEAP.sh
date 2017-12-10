#!/bin/bash

echo "Install JBoss EAP."

# Install.
cd /vagrant
java -jar jboss-eap-7.0.0-installer.jar auto.xml

# Config as a Service
cp /opt/eap7/bin/init.d/jboss-eap.conf /etc/default
cp /opt/eap7/bin/init.d/jboss-eap-rhel.sh /etc/init.d
chmod +x /etc/init.d/jboss-eap-rhel.sh

# Config service.
sed -i "s/^.*JBOSS_HOME.*$/JBOSS_HOME=\"\/opt\/eap7\"/g" /etc/default/jboss-eap.conf
sed -i "s/^.*JBOSS_USER.*$/JBOSS_USER=root/g" /etc/default/jboss-eap.conf
# Enable remote access.
sed -i "$ a\JBOSS_OPTS=\"\$JBOSS_OPTS -Djboss.bind.address.management=0.0.0.0 -Djboss.bind.address=0.0.0.0\"" /etc/default/jboss-eap.conf

# Enable and start service.
chkconfig --add jboss-eap-rhel.sh
chkconfig jboss-eap-rhel.sh on
systemctl start jboss-eap-rhel
sleep 5

# Add JDBC drivers.
/opt/eap7/bin/jboss-cli.sh << EOF
module add --name=com.mysql --slot=main --resources=/vagrant/JDBC_Drivers/mysql-connector-java-5.1.41-bin.jar --dependencies=javax.api,javax.transaction.api

module add --name=oracle.jdbc --slot=main --resources=/vagrant/JDBC_Drivers/ojdbc6-11.2.0.4.jar --dependencies=javax.api,javax.transaction.api

module add --name=org.postgresql --slot=main --resources=/vagrant/JDBC_Drivers/postgresql-42.0.0.jar --dependencies=javax.api,javax.transaction.api

connect

/subsystem=ee:write-attribute(name=global-modules,value=[{name=com.mysql},{name=oracle.jdbc},{name=org.postgresql}]

/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource, driver-class-name=com.mysql.jdbc.Driver)

/subsystem=datasources/jdbc-driver=oraclexe:add(driver-name=oraclexe,driver-module-name=oracle.jdbc,driver-xa-datasource-class-name=oracle.jdbc.xa.OracleXADataSource)

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)

data-source add --name=MySqlDSvg --jndi-name=java:jboss/MySqlDSvg --driver-name=mysql --connection-url=jdbc:mysql://192.168.0.52:3306/test --user-name=tester --password=P@ssw0rd --validate-on-match=true --background-validation=false

data-source add --name=PostgreSqlDSvg --jndi-name=java:jboss/PostgreSqlDSvg --driver-name=postgresql --connection-url=jdbc:postgresql://192.168.0.52:5432/test --user-name=tester --password=P@ssw0rd --validate-on-match=true --background-validation=false
EOF
