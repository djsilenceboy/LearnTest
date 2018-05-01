#!/bin/sh
#
# Purpose:
#   Backup offline for a Database.
#
# Usage:
#   ThisScript <DbName> <BackupFullPath>
#
# exit: 0
#       1 - Input parameter error.
#
# Notice:
# 1. When calling this script manually by logging as DB2 user.
#  a. This is the optional way to do backup temporarily.
#  b. It is ok to set up environment with "db2profile" or not. Because the environment has already been set up by logging as DB2 user.
#
# 2. When calling this script from cron job created for DB2 user.
#  a. This is the preferred way to do backup periodically.
#  b. It must call "db2profile" to set up environment.
#
# 3. When calling this script from cron job created for root user.
#  a. It must "su - <DB2 user>" first.
#  b. It must call "db2profile" to set up environment.
#
# Update log: (date / version / author : comments)
# 2014-09-29 / 1.0.0 / Du Jiang : Creation

if [ $# -ne 2 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName> <BackupFullPath>"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin backup offline: `date`"
echo "Database = "$1
echo "Backup full path = "$2
echo "----------------------------------------"

db2 -tv +p << EOF
CONNECT TO $1;
QUIESCE DATABASE IMMEDIATE FORCE CONNECTIONS;
CONNECT RESET;
DEACTIVATE DATABASE $1;
BACKUP DATABASE $1 TO $2 COMPRESS WITHOUT PROMPTING;
ACTIVATE DATABASE $1;
CONNECT TO $1;
UNQUIESCE DATABASE;
CONNECT RESET;
EOF

echo "End backup offline: `date`"
echo "============================================================"
