#!/bin/bash

echo "Dnf install Development Tools."

# Tool like gcc.
dnf group install -y "Development Tools"

# For some python module, ansible plugin.
dnf install -y python36-devel openssl-devel
