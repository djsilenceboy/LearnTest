#!/bin/bash
#
# Purpose:
#   Linux: Check user ID age.
#
# Usage:
#   To be uploaded and run on remote host by Ansible:
#   ThisScript <HostID> <ExtraParameters>
#
#   HostID:
#     A string defined in Ansible host file.
#   ExtraParameters:
#     UserID
#
# Update log: (date / version / author : comments)
# 2017-09-13 / 1.0.0 / Du Jiang : Creation

HostID=$1
UserID=$2

printf '%.0s-' {1..80}; echo
echo "Date: "`date +"%F %H:%M:%S %Z%z"`
echo "Host ID: "$HostID
echo -n "Hostname and IP: "
echo -n `hostname`
echo -n ", "
# IP.
echo `hostname -I | awk '{print $1}'`

printf '%.0s-' {1..60}; echo
chage -l $UserID

printf '%.0s=' {1..80}; echo
