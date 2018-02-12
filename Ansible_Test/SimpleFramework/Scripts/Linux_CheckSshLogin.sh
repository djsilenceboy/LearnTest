#!/bin/bash
#
# Purpose:
#   Linux: Check SSH login.
#
# Usage:
#   To be uploaded and run on remote host by Ansible:
#   ThisScript <HostID>
#
#   HostID:
#     A string defined in Ansible host file.
#
# Update log: (date / version / author : comments)
# 2018-01-10 / 1.0.0 / Du Jiang : Creation

HostID=$1

echo -n $HostID
echo -n ", "
echo -n `hostname`
echo -n ", "
echo -n "Login ok"
