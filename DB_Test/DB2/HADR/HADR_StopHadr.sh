#!/bin/sh
#
# Purpose:
#   HADR: Stop HADR on database.
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
# 2. For Standby database, it must be deactivated first before stopping HADR.
#
# Update log: (date / version / author : comments)
# 2015-09-02 / 1.0.0 / Du Jiang : Creation

if [ $# -ne 1 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName>"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin start: `date`"
echo "Database = "$1
echo "----------------------------------------"
echo "Before:"
echo
db2pd -hadr -db $1
echo "----------------------------------------"
echo "Before (Listening port):"
echo
netstat -an | grep ":500"
echo "----------------------------------------"
db2 stop hadr on db $1
echo "----------------------------------------"
echo "After:"
echo
db2pd -hadr -db $1
echo "----------------------------------------"
echo "After (Listening port):"
echo
netstat -an | grep ":500"
echo "----------------------------------------"
echo "End start: `date`"
echo "============================================================"
