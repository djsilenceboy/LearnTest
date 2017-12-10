#!/bin/bash

echo "Yum install Development Tools."

# Tool like gcc.
yum groupinstall -y "Development Tools"

# For some python module, ansible plugin.
yum install -y python-devel openssl-devel
