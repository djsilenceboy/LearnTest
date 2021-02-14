#!/bin/bash

echo "Dnf upgrade and install some useful tools"

# The default image already includes: xxx

# Dnf upgrade
dnf upgrade -y

# Some useful tools.
dnf install -y bind-utils cifs-utils git net-tools perl python3 traceroute unzip whois yum-utils zip java-1.8.0-openjdk

# Upgrade Python pip.
python3 -m pip install --upgrade pip
