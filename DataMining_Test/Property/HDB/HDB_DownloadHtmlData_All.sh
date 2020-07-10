#!/bin/bash

# DATE_RANGE:
# 6 : Last 6 month
# 12: Last 12 month
DATE_RANGE=$1
DATE_TAG=$2

BASE_FOLDER="./HtmlData_${DATE_TAG}"
FILE_PREFIX="HDB_Trans_${DATE_TAG}_"

CURRENT_FOLDER=$(pwd)

echo "================================================================================"
# Generate download scripts.
echo "Date: "$(date +"%FT%T%z")
echo "DATE_TAG = "$DATE_TAG
echo "BASE_FOLDER = "$BASE_FOLDER
echo "FILE_PREFIX = "$FILE_PREFIX
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo
echo "Download ..."

mkdir -p ${BASE_FOLDER}

N=1
for FLAT_TYPE in 01 02 03 04 05 06 08
do
	${CURRENT_FOLDER}/HDB_DownloadHtmlData_One.sh 1 ${FLAT_TYPE} ${DATE_RANGE} ${BASE_FOLDER} "${FILE_PREFIX}${FLAT_TYPE}" &
	pids[$N]=$!
	N=$(($N + 1))
done

# Wait for all pids.
for pid in ${pids[*]}
do
	echo "Wait for PID $pid ..."
	wait $pid
done
echo "All PIDs completed."

cd ${CURRENT_FOLDER}

echo "All download completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
