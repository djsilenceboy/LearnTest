#!/bin/sh
#
# Purpose:
#   Restore offline for a Database (No rolling forward).
#
# Usage:
#   ThisScript <DbName> <BackupFullPath> <Timestamp>
#
# exit: 0
#       1 - Input parameter error.
#
# Notice:
# 1. When calling this script manually by logging as DB2 user.
#  a. This is the optional way to do backup temporarily.
#  b. It is ok to set up environment with "db2profile" or not. Because the environment has already been set up by logging as DB2 user.
#
# Update log: (date / version / author : comments)
# 2014-09-29 / 1.0.0 / Du Jiang : Creation
# 2015-04-28 / 1.1.0 / Du Jiang : Add "WITHOUT ROLLING FORWARD"

if [ $# -ne 3 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName> <BackupFullPath> <Timestamp>"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin restore offline (No rolling forward): `date`"
echo "Database = "$1
echo "Backup full path = "$2
echo "Timestamp = "$3
echo "----------------------------------------"
db2 -tv +p << EOF
CONNECT TO $1;
QUIESCE DATABASE IMMEDIATE FORCE CONNECTIONS;
CONNECT RESET;
DEACTIVATE DATABASE $1;
RESTORE DATABASE $1 FROM $2 TAKEN AT $3 WITHOUT ROLLING FORWARD;
ACTIVATE DATABASE $1;
CONNECT TO $1;
UNQUIESCE DATABASE;
CONNECT RESET;
EOF
echo "----------------------------------------"
echo "End restore offline (No rolling forward): `date`"
echo "============================================================"
