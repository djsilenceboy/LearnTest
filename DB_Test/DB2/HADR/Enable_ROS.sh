#!/bin/sh
#
# Purpose:
#   Enable Reads on standby (ROS) for Database using HADR.
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
# 2015-03-25 / 1.0.0 / Du Jiang : Creation

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin set DB config: `date`"
echo "----------------------------------------"
echo "Before:"
echo
db2set -all
echo "----------------------------------------"
db2set DB2_HADR_ROS=ON
db2set DB2_STANDBY_ISO=UR
echo "----------------------------------------"
echo "After:"
echo
db2set -all
echo "----------------------------------------"
echo "End set DB config: `date`"
echo "============================================================"
