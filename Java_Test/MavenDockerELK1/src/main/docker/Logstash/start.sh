#!/bin/bash

set -v -x

# Set "-e ELASTICSEARCH_URL=xxx" while create/run docker container. 
echo "ELASTICSEARCH_URL = "$ELASTICSEARCH_URL
sed -i "s/ELASTICSEARCH_URL/$ELASTICSEARCH_URL/g" /etc/logstash/conf.d/default.conf
cat /etc/logstash/conf.d/default.conf

logstash -f /etc/logstash/conf.d/default.conf
