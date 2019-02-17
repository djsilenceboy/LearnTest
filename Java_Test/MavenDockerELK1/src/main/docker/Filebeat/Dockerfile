FROM centos:7

# Must use root user.

RUN yum update -y && \
    yum install -y initscripts && \
    yum clean all

# Install filebeat.
RUN rpm -ivh --nodeps https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-5.6.2-x86_64.rpm

ENV ELASTIC_CONTAINER true
ENV PATH=/usr/share/filebeat/bin:$PATH

# Config file.
COPY filebeat.yml /etc/filebeat/filebeat.yml
RUN chmod 644 /etc/filebeat/filebeat.yml

RUN set -x && \
    ls -ld /etc/init.d/ && \
    ls -l /etc/init.d/ && \
    ls -ld /etc/filebeat && \
    ls -l /etc/filebeat && \
    ls -ld /usr/share/filebeat && \
    ls -l /usr/share/filebeat

# Docker does not have full systemctl/init.d.
# CMD ["/etc/init.d/filebeat", "start"]
CMD ["filebeat", "-e", "-c", "/etc/filebeat/filebeat.yml"]
