#!/bin/sh
#
# Purpose:
#   Reset (Remove) automatic client reroute (ACR) for Database node 2.
#
# Notice:
# 1. When calling this script manually by logging as DB2 user.
#  a. This is the optional way to do backup temporarily.
#  b. It is ok to set up environment with "db2profile" or not. Because the environment has already been set up by logging as DB2 user.
#
# Update log: (date / version / author : comments)
# 2015-09-03 / 1.0.0 / Du Jiang : Creation

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin set DB config: `date`"
echo "----------------------------------------"
echo "Before:"
echo
db2 list db directory
echo "----------------------------------------"

db2 -tv +p << EOF
UPDATE ALTERNATE SERVER FOR DATABASE DERDB USING HOSTNAME NULL PORT NULL;
UPDATE ALTERNATE SERVER FOR DATABASE SCAADPDB USING HOSTNAME NULL PORT NULL;
UPDATE ALTERNATE SERVER FOR DATABASE GPOSTRDB USING HOSTNAME NULL PORT NULL;
EOF

echo "----------------------------------------"
echo "After:"
echo
db2 list db directory
echo "----------------------------------------"
echo "End set DB config: `date`"
echo "============================================================"
