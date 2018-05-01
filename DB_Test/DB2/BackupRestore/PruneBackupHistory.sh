#!/bin/sh
#
# Purpose:
#   Prune backup history (and image files and log files).
#
# Usage:
#   ThisScript <DbName> <Date>
#
#   <Date>: A description of the date before current.
#           Format: "30 days ago", "4 weeks ago", "1 month ago", etc.
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

if [ $# -ne 2 ]; then
	echo "Usage: "
	echo "  `basename $0` <DbName> <Date>"
	echo "   <Date>: A description of the date before current."
	echo "   Format: \"30 days ago\", \"4 weeks ago\", \"1 month ago\", etc."
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

DbName=$1
ExpectedPruneDate=`date +"%Y%m%d000000000" -d "$2"`

echo "============================================================"
echo "Begin prune: `date`"
echo "Database name = "$DbName
echo "Expected prune date = "$ExpectedPruneDate
echo "----------------------------------------"

# 1. Get all history contents.
# 2. Find all timestamp lines.
#    And timestamp should be less then expected.
#    And keep only timestamp and log file name.
# 3. Keep the last record. That means the latest date before expected date.
HistoryRecord=`db2 list history backup all for $DbName | awk -v EPD=$ExpectedPruneDate '$0 ~/B  D/ && $3 <= EPD {print $3" "$6}' | sed -n '$p'`

if [ -n "$HistoryRecord" ]; then
	echo "The latest history record before expected date = "$HistoryRecord
	TEMP_IFS=$IFS
	IFS=" "

	while read HistoryTimestamp HistoryLogFileName
	do
		# Covert from "yyyyMMddHHmmssSSS" to "yyyyMMddHHmmss".
		HistoryTimestamp=`echo $HistoryTimestamp | cut -c1-14`
		echo "History timestamp = "$HistoryTimestamp
		echo "History log file name = "$HistoryLogFileName

		# Prune history less than or equal to specified timestamp.
		db2 -tv +p << EOF
CONNECT TO $DbName;
PRUNE HISTORY $HistoryTimestamp WITH FORCE OPTION AND DELETE;
EOF
	done <<< "$HistoryRecord"

	IFS=$TEMP_IFS
else
	echo "No older history record to be pruned."
fi
echo "----------------------------------------"
echo "End prune: `date`"
echo "============================================================"
