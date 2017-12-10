#!/bin/bash

echo "Install python modules."

# Following already be installed:
# python python-devel openssl-devel

# Repo for pip.
yum install -y epel-release
yum install -y python-pip

# Upgrade pip.
pip install --upgrade pip
