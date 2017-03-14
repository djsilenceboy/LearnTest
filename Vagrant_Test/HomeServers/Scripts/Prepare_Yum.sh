#!/bin/bash

echo "Yum update and install tools."

# The default image already includes:
# net-tools, perl

# Update.
yum update -y

# Some useful tools.
yum install -y bind-utils bridge-utils cifs-utils git screen traceroute unzip whois zip 

# JDK.
yum install -y java-1.8.0-openjdk
