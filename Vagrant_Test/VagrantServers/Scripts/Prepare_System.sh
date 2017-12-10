#!/bin/bash

echo "Config system."

# Turn off firewall.
# The default image does not have iptables, ipset.
systemctl stop firewalld
systemctl disable firewalld
# systemctl stop iptables
# systemctl disable iptables
# systemctl stop ipset
# systemctl disable ipset

# Limit kept kernels.
sed -i "s/\(installonly_limit=\).*$/\12/g" /etc/yum.conf
