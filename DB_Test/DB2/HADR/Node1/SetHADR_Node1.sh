#!/bin/sh
#
# Purpose:
#   Set high availability disaster recovery (ACR) for Database node 1.
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
echo "Before (DERDB):"
echo
db2 GET DB CFG FOR DERDB | grep HADR
echo "----------------------------------------"

db2 -tv +p << EOF
UPDATE DB CFG FOR DERDB USING HADR_LOCAL_HOST gpo-hc-db2-1.sdad.sl.ibm.com;
UPDATE DB CFG FOR DERDB USING HADR_LOCAL_SVC DB2_HADR_DERDB_1;
UPDATE DB CFG FOR DERDB USING HADR_SYNCMODE NEARSYNC;

UPDATE DB CFG FOR DERDB USING HADR_REMOTE_HOST gpo-hc-db2-2.sdad.sl.ibm.com;
UPDATE DB CFG FOR DERDB USING HADR_REMOTE_SVC DB2_HADR_DERDB_2;
UPDATE DB CFG FOR DERDB USING HADR_REMOTE_INST db2inst1;
UPDATE DB CFG FOR DERDB USING HADR_TARGET_LIST gpo-hc-db2-2.sdad.sl.ibm.com:DB2_HADR_DERDB_2;

UPDATE DB CFG FOR DERDB USING HADR_TIMEOUT 120;
UPDATE DB CFG FOR DERDB USING HADR_PEER_WINDOW 120;
EOF

echo "----------------------------------------"
echo "After (DERDB):"
echo
db2 GET DB CFG FOR DERDB | grep HADR
echo "----------------------------------------"
echo "Before (SCAADPDB):"
echo
db2 GET DB CFG FOR SCAADPDB | grep HADR
echo "----------------------------------------"

db2 -tv +p << EOF
UPDATE DB CFG FOR SCAADPDB USING HADR_LOCAL_HOST gpo-hc-db2-1.sdad.sl.ibm.com;
UPDATE DB CFG FOR SCAADPDB USING HADR_LOCAL_SVC DB2_HADR_SCAADPDB_1;
UPDATE DB CFG FOR SCAADPDB USING HADR_SYNCMODE NEARSYNC;

UPDATE DB CFG FOR SCAADPDB USING HADR_REMOTE_HOST gpo-hc-db2-2.sdad.sl.ibm.com;
UPDATE DB CFG FOR SCAADPDB USING HADR_REMOTE_SVC DB2_HADR_SCAADPDB_2;
UPDATE DB CFG FOR SCAADPDB USING HADR_REMOTE_INST db2inst1;
UPDATE DB CFG FOR SCAADPDB USING HADR_TARGET_LIST gpo-hc-db2-2.sdad.sl.ibm.com:DB2_HADR_SCAADPDB_2;

UPDATE DB CFG FOR SCAADPDB USING HADR_TIMEOUT 120;
UPDATE DB CFG FOR SCAADPDB USING HADR_PEER_WINDOW 120;
EOF

echo "----------------------------------------"
echo "After (SCAADPDB):"
echo
db2 GET DB CFG FOR SCAADPDB | grep HADR
echo "----------------------------------------"
echo "Before (GPOSTRDB):"
echo
db2 GET DB CFG FOR GPOSTRDB | grep HADR
echo "----------------------------------------"

db2 -tv +p << EOF
UPDATE DB CFG FOR GPOSTRDB USING HADR_LOCAL_HOST gpo-hc-db2-1.sdad.sl.ibm.com;
UPDATE DB CFG FOR GPOSTRDB USING HADR_LOCAL_SVC DB2_HADR_GPOSTRDB_1;
UPDATE DB CFG FOR GPOSTRDB USING HADR_SYNCMODE NEARSYNC;

UPDATE DB CFG FOR GPOSTRDB USING HADR_REMOTE_HOST gpo-hc-db2-2.sdad.sl.ibm.com;
UPDATE DB CFG FOR GPOSTRDB USING HADR_REMOTE_SVC DB2_HADR_GPOSTRDB_2;
UPDATE DB CFG FOR GPOSTRDB USING HADR_REMOTE_INST db2inst1;
UPDATE DB CFG FOR GPOSTRDB USING HADR_TARGET_LIST gpo-hc-db2-2.sdad.sl.ibm.com:DB2_HADR_GPOSTRDB_2;

UPDATE DB CFG FOR GPOSTRDB USING HADR_TIMEOUT 120;
UPDATE DB CFG FOR GPOSTRDB USING HADR_PEER_WINDOW 120;
EOF

echo "----------------------------------------"
echo "After (GPOSTRDB):"
echo
db2 GET DB CFG FOR GPOSTRDB | grep HADR
echo "----------------------------------------"
echo "End set DB config: `date`"
echo "============================================================"
