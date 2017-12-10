#!/bin/bash

echo "Yum update and install tools."

# The default image already includes:
#

# Update.
yum update -y

# Some useful tools.
yum install -y bind-utils bridge-utils cifs-utils git net-tools perl screen traceroute unzip whois yum-utils zip

# JDK.
yum install -y java-1.8.0-openjdk
