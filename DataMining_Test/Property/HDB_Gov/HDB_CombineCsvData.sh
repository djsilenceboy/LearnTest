#!/bin/bash

DATE_RANGE=20221217

CURRENT_FOLDER=$(pwd)
CURRENT_NAME=$(basename $0)
CURRENT_NAME=${CURRENT_NAME/.sh}
CSV_DATA_FOLDER=${CURRENT_FOLDER}/CSV_Data/${DATE_RANGE}
PROCESSED_DATA_FOLDER=${CURRENT_FOLDER}/ProcessedDataCombined/${DATE_RANGE}
LOG_FILE=${CURRENT_NAME}_${DATE_TAG}.log
TMP_FOLDER=${CURRENT_FOLDER}/tmp

HdbTrans_FileName=HDB_Trans_${DATE_RANGE}_M.csv

mkdir -p ${TMP_FOLDER}
mkdir -p ${PROCESSED_DATA_FOLDER}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo "CSV_DATA_FOLDER = "$CSV_DATA_FOLDER
echo "PROCESSED_DATA_FOLDER = "$PROCESSED_DATA_FOLDER
echo "LOG_FILE = "${TMP_FOLDER}"/"${LOG_FILE}
echo 
echo "Start combining ..."

ls -l ${CSV_DATA_FOLDER}/*.csv | grep '2015\|2017'

echo 'month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,remaining_lease,resale_price' > ${PROCESSED_DATA_FOLDER}/${HdbTrans_FileName}

grep -h -v 'street_name' --include=${CSV_DATA_FOLDER}/*{2015,2017}* ${CSV_DATA_FOLDER}/*.csv >> ${PROCESSED_DATA_FOLDER}/${HdbTrans_FileName}

echo "Combine completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
