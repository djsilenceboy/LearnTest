#!/bin/sh
#
# Purpose:
#   Set automatic client reroute (ACR) for Database node 1.
#
# Notice:
# 1. When calling this script manually by logging as DB2 user.
#  a. This is the optional way to do backup temporarily.
#  b. It is ok to set up environment with "db2profile" or not. Because the environment has already been set up by logging as DB2 user.
#
# Update log: (date / version / author : comments)
# 2015-03-25 / 1.0.0 / Du Jiang : Creation
# 2015-08-18 / 1.1.0 / Du Jiang : Add GPOSTRDB

. /home/db2inst1/sqllib/db2profile

echo "============================================================"
echo "Begin set DB config: `date`"
echo "----------------------------------------"
echo "Before:"
echo
db2 list db directory
echo "----------------------------------------"

db2 -tv +p << EOF
UPDATE ALTERNATE SERVER FOR DATABASE DERDB USING HOSTNAME gpo-hc-db2-2.sdad.sl.ibm.com PORT 50000;
UPDATE ALTERNATE SERVER FOR DATABASE SCAADPDB USING HOSTNAME gpo-hc-db2-2.sdad.sl.ibm.com PORT 50000;
UPDATE ALTERNATE SERVER FOR DATABASE GPOSTRDB USING HOSTNAME gpo-hc-db2-2.sdad.sl.ibm.com PORT 50000;
EOF

echo "----------------------------------------"
echo "After:"
echo
db2 list db directory
echo "----------------------------------------"
echo "End set DB config: `date`"
echo "============================================================"
