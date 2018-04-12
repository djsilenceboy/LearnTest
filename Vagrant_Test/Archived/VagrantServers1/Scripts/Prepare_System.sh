#!/bin/bash

echo "Config system."

# Turn off firewall.
# The default image does not have firewalld, iptables, ipset.
# systemctl stop firewalld
# systemctl disable firewalld
# systemctl stop iptables
# systemctl disable iptables

# Limit kept kernels.
sed -i "s/\(installonly_limit=\).*$/\12/g" /etc/yum.conf
