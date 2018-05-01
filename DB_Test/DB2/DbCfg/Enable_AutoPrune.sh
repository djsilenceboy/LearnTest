#!/bin/sh
#
# Purpose:
#   Enable auto prune history for a Database.
#
# Usage:
#   ThisScript <DbName>
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
# 2015-04-16 / 1.0.0 / Du Jiang : Creation

if [ $# -ne 1 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName>"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin enable auto prune: `date`"
echo "----------------------------------------"
echo "Before:"
echo
db2 get db cfg for $1 | grep -E "AUTO_DEL_REC_OBJ|NUM_DB_BACKUPS|REC_HIS_RETENTN"
echo "----------------------------------------"

db2 -tv +p << EOF
CONNECT TO $1;
QUIESCE DATABASE IMMEDIATE FORCE CONNECTIONS;
UNQUIESCE DATABASE;
CONNECT RESET;
CONNECT TO $1;
UPDATE DATABASE CONFIGURATION USING AUTO_DEL_REC_OBJ ON;
UPDATE DATABASE CONFIGURATION USING NUM_DB_BACKUPS 7;
UPDATE DATABASE CONFIGURATION USING REC_HIS_RETENTN 30;
CONNECT RESET;
DEACTIVATE DATABASE $1;
EOF

echo "----------------------------------------"
echo "After:"
echo
db2 get db cfg for $1 | grep -E "AUTO_DEL_REC_OBJ|NUM_DB_BACKUPS|REC_HIS_RETENTN"
echo "----------------------------------------"
echo "End enable auto prune: `date`"
echo "============================================================"
