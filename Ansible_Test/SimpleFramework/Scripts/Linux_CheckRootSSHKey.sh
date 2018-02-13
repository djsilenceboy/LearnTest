#!/bin/bash
#
# Purpose:
#   Linux: Check root user's SSH keys.
#
# Usage:
#   To be uploaded and run on remote host by Ansible:
#   ThisScript <HostID>
#
#   HostID:
#     A string defined in Ansible host file.
#
# Update log: (date / version / author : comments)
# 2017-08-21 / 1.0.0 / Du Jiang : Creation

HostID=$1

KeyFile=~root/.ssh/authorized_keys
TempKeyFile=/tmp/SshKey_$(date +"%F_%H%M%S").tmp

while read SSHKey
do
	case $SSHKey in
		# Ignore comment line, empty line.
		"" | " "* | \#*)
			continue
		;;
	esac

	echo "$SSHKey" > $TempKeyFile
	Var2=$(ssh-keygen -lf $TempKeyFile)
	Var2=${Var2#* }
	FingerPrint=${Var2%% *}
	echo $HostID", "$FingerPrint", "$SSHKey
done < "$KeyFile"
