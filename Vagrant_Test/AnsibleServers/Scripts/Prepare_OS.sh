#!/bin/bash

echo "Prepare: dnf upgrade"
dnf upgrade -y

echo "Prepare: Some useful tools"
dnf install -y bind-utils cifs-utils git net-tools perl python3 traceroute unzip whois yum-utils zip

echo "Prepare: Python pip"
python3 -m pip install --upgrade pip
