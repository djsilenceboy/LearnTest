#!/bin/bash

# Download JDBC drivers.
cd /tmp
curl -L -o mysql-connector-java-8.0.13.jar http://central.maven.org/maven2/mysql/mysql-connector-java/8.0.13/mysql-connector-java-8.0.13.jar
curl -L -o ojdbc6-11.2.0.4.0-atlassian-hosted.jar https://packages.atlassian.com/maven-3rdparty/com/oracle/ojdbc6/11.2.0.4.0-atlassian-hosted/ojdbc6-11.2.0.4.0-atlassian-hosted.jar
curl -L -o postgresql-42.2.5.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar

# Add JDBC drivers.
/opt/eap7/bin/jboss-cli.sh << EOF
module add --name=com.mysql --slot=main --resources=/tmp/mysql-connector-java-8.0.13.jar --dependencies=javax.api,javax.transaction.api

module add --name=oracle.jdbc --slot=main --resources=/tmp/ojdbc6-11.2.0.4.0-atlassian-hosted.jar --dependencies=javax.api,javax.transaction.api

module add --name=org.postgresql --slot=main --resources=/tmp/postgresql-42.2.5.jar --dependencies=javax.api,javax.transaction.api

connect

/subsystem=ee:write-attribute(name=global-modules,value=[{name=com.mysql},{name=oracle.jdbc},{name=org.postgresql}]

/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource, driver-class-name=com.mysql.jdbc.Driver)

/subsystem=datasources/jdbc-driver=oraclexe:add(driver-name=oraclexe,driver-module-name=oracle.jdbc,driver-xa-datasource-class-name=oracle.jdbc.xa.OracleXADataSource)

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)

data-source add --name=MySqlDSvg --jndi-name=java:jboss/MySqlDSvg --driver-name=mysql --connection-url=jdbc:mysql://192.168.18.11:3306/test --user-name=tester --password=P@ssw0rd --validate-on-match=true --background-validation=false

data-source add --name=PostgreSqlDSvg --jndi-name=java:jboss/PostgreSqlDSvg --driver-name=postgresql --connection-url=jdbc:postgresql://192.168.18.11:5432/test --user-name=tester --password=P@ssw0rd --validate-on-match=true --background-validation=false
EOF
