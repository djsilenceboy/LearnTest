#!/bin/sh
#
# Purpose:
#   HADR: Show HADR status.
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
# Update log: (date / version / author : comments)
# 2015-03-25 / 1.0.0 / Du Jiang : Creation
# 2015-08-06 / 1.1.0 / Du Jiang : Generalized

if [ $# -ne 1 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName>"
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin status: `date`"
echo "Database = "$1
echo "----------------------------------------"
db2pd -hadr -db $1
echo "----------------------------------------"
echo "End status: `date`"
echo "============================================================"
