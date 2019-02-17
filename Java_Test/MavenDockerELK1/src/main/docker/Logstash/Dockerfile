FROM centos:7

# Install Java and the "which" command, which is needed by Logstash's shell scripts.
RUN yum update -y && \
    yum install -y java-1.8.0-openjdk which && \
    yum clean all

# RUN set -x && \
#     ls -l /usr/bin/java && \
#     ls -l /etc/alternatives && \
#     ls -l /usr/lib/jvm

ENV JAVA_HOME=/etc/alternatives/jre
ENV PATH=$JAVA_HOME/bin:$PATH

# Provide a non-root user to run the process.
RUN useradd -M -d /usr/share/logstash logstash

# Install Logstash.
RUN rpm -ivh --nodeps https://artifacts.elastic.co/downloads/logstash/logstash-5.6.2.rpm && \
    chown -R logstash:logstash /usr/share/logstash/ && \
    chmod -R a+rw /usr/share/logstash && \
    find /usr/share/logstash -type d -exec chmod g+s {} \;

ENV ELASTIC_CONTAINER true
ENV PATH=/usr/share/logstash/bin:$PATH

WORKDIR /usr/share/logstash
# To fix a bug in release 5.x.
RUN ln -s /etc/logstash /usr/share/logstash/config

# Provide a minimal configuration, so that simple invocations will provide
# a good experience.
COPY logstash.yml config/
COPY log4j2.properties config/
COPY default.conf /etc/logstash/conf.d/
COPY start.sh /usr/local/bin/
 
# Ensure Logstash gets a UTF-8 locale by default.
ENV LANG='en_US.UTF-8' LC_ALL='en_US.UTF-8'

RUN chown -R logstash:logstash /etc/logstash/ && \
    chmod -R a+r /etc/logstash && \
    chown logstash:logstash /usr/local/bin/start.sh && \
    chmod a+rwx /usr/local/bin/start.sh && \
    find /etc/logstash -type d -exec chmod g+s {} \;

RUN set -x && \
    ls -ld /etc/logstash && \
    ls -l /etc/logstash && \
    ls -ld /usr/share/logstash && \
    ls -l /usr/share/logstash && \
    ls -l /usr/local/bin/start.sh

USER logstash

EXPOSE 9600 5044

CMD ["/bin/bash", "/usr/local/bin/start.sh"]
