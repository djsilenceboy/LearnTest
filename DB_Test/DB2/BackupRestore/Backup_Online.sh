#!/bin/sh
#
# Purpose:
#   Backup online for a Database.
#
# Usage:
#   ThisScript <DbName> <BackupFullPath> <BackupMode>
#
#   <BackupMode>: [full, incremental, delta]
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
# 2014-12-17 / 1.0.0 / Du Jiang : Creation

usage() {
	echo "Usage: "
	echo "  `basename $0` <DbName> <BackupFullPath> <BackupMode>"
	echo "  <BackupMode>: [full, incremental, delta]"
}

if [ $# -ne 3 ]; then
	usage
	exit 1
fi

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin backup online $3: `date`"
echo "----------------------------------------"
case "$3" in
	full)
		db2 -tv +p << EOF
BACKUP DATABASE $1 ONLINE TO $2 COMPRESS WITHOUT PROMPTING;
EOF
		;;
	incremental)
		db2 -tv +p << EOF
BACKUP DATABASE $1 ONLINE INCREMENTAL TO $2 COMPRESS WITHOUT PROMPTING;
EOF
		;;
	delta)
		db2 -tv +p << EOF
BACKUP DATABASE $1 ONLINE INCREMENTAL DELTA TO $2 COMPRESS WITHOUT PROMPTING;
EOF
		;;
	*)
		usage
		exit 1
esac
echo "----------------------------------------"
echo "End backup online $3: `date`"
echo "============================================================"
