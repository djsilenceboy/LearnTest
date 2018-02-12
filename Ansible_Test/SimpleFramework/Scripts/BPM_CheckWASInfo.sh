#!/bin/bash
#
# Purpose:
#   BPM: Check WAS info.
#
# Usage:
#   To be uploaded and run on remote host by Ansible:
#   ThisScript <HostID>
#
#   HostID:
#     A string defined in Ansible host file.
#
# exit: 0
#
# Update log: (date / version / author : comments)
# 2017-04-25 / 1.0.0 / Du Jiang : Creation

HostID=$1

printf '%.0s-' {1..80}; echo
echo "Date: "`date +"%F %H:%M:%S %Z%z"`
echo "Host ID: "$HostID
echo -n "Hostname and IP: "
echo -n `hostname`
echo -n ", "
# IP.
echo `hostname -I | awk '{print $1}'`

printf '%.0s-' {1..60}; echo
echo "PID  Node  Server"
# Find WAS java instance.
ps -efww | grep 'java -' | grep -E 'WsServer| -Dwas' | awk '{ printf($2); for (i=NF-1; i<=NF; i++) printf("  %s", $i); printf("\n") }'

printf '%.0s=' {1..80}; echo
