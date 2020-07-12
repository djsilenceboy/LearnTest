#!/bin/bash

TOTAL_PAGES=176
DATE_TAG=$(date +"%Y%m%d")

CURRENT_FOLDER=$(pwd)
CURRENT_NAME=$(basename $0)
CURRENT_NAME=${CURRENT_NAME/.sh}
LOG_FILE=${CURRENT_NAME}_${DATE_TAG}.log
TMP_FOLDER=${CURRENT_FOLDER}/tmp

mkdir -p ${TMP_FOLDER}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo "LOG_FILE = "${TMP_FOLDER}"/"${LOG_FILE}
echo 
echo "Start downloading ..."

${CURRENT_FOLDER}/Metacritic_DownloadHtmlData_All.sh ${TOTAL_PAGES} "${DATE_TAG}" |& tee ${TMP_FOLDER}/${LOG_FILE}

echo "Download completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
