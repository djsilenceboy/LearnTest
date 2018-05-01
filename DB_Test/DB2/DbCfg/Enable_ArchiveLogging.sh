#!/bin/sh
#
# Purpose:
#   Enable archive logging for a Database.
#
# Usage:
#   ThisScript <DbName> <ArchivedLogFullPath>
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
# 2014-10-17 / 1.0.0 / Du Jiang : Creation
# 2015-04-16 / 1.1.0 / Du Jiang : Add before/after status.
# 2015-04-24 / 1.0.0 / Du Jiang : Value adjust for LOGARCHMETH1, LOGINDEXBUILD

if [ $# -ne 2 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName> <ArchivedLogFullPath>"
	echo "  <ArchivedLogFullPath>: Sample: /home/db2inst1/db2backup/ARCHIVED_LOGS"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

DbName=$1
ArchivedLogFullPath=$2

echo "============================================================"
echo "Begin enable archive logging: `date`"
echo "----------------------------------------"
echo "Before:"
echo
db2 get db cfg for $DbName | grep -E "LOGARCHMETH1|LOGPRIMARY|LOGSECOND|LOGFILSIZ|LOGINDEXBUILD"
echo "----------------------------------------"

db2 -tv +p << EOF
CONNECT TO $DbName;
QUIESCE DATABASE IMMEDIATE FORCE CONNECTIONS;
UNQUIESCE DATABASE;
CONNECT RESET;
CONNECT TO $DbName;
UPDATE DATABASE CONFIGURATION USING LOGARCHMETH1 DISK:$ArchivedLogFullPath;
UPDATE DATABASE CONFIGURATION USING LOGINDEXBUILD ON;
UPDATE DATABASE CONFIGURATION USING LOGPRIMARY 13 LOGSECOND 12 LOGFILSIZ 16384;
CONNECT RESET;
DEACTIVATE DATABASE $DbName;
EOF

echo "----------------------------------------"
echo "After:"
echo
db2 get db cfg for $DbName | grep -E "LOGARCHMETH1|LOGPRIMARY|LOGSECOND|LOGFILSIZ|LOGINDEXBUILD"
echo "----------------------------------------"
echo "End enable archive logging: `date`"
echo "============================================================"
