#!/bin/bash

# Update.
yum update -y

# Some useful tools.
yum install -y bind-utils bridge-utils cifs-utils git net-tools perl screen traceroute unzip whois yum-utils zip

# Tool like gcc.
yum groupinstall -y "Development Tools"

# For some python module, ansible plugin.
yum install -y python-devel openssl-devel

# Repo for pip.
yum install -y epel-release
yum install -y python-pip

# Upgrade pip.
pip install --upgrade pip
