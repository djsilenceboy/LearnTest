#!/bin/bash

FROM_DATE=201706
TO_DATE=202005
FROM_PERIOD="JUN 2017"
TO_PERIOD="MAY 2020"

CURRENT_FOLDER=$(pwd)
CURRENT_NAME=$(basename $0)
CURRENT_NAME=${CURRENT_NAME/.sh}
DATE_RANGE="${FROM_DATE}_${TO_DATE}"
LOG_FILE=${CURRENT_NAME}_${DATE_RANGE}.log
TMP_FOLDER=${CURRENT_FOLDER}/tmp

mkdir -p ${TMP_FOLDER}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo "LOG_FILE = "${TMP_FOLDER}"/"${LOG_FILE}
echo 
echo "Start downloading ..."

${CURRENT_FOLDER}/URA_DownloadHtmlData_Run.sh ${FROM_DATE} ${TO_DATE} "${FROM_PERIOD}" "${TO_PERIOD}" |& tee ${TMP_FOLDER}/${LOG_FILE}

echo "Download completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
