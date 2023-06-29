#!/bin/bash

FROM_DATE=201608
TO_DATE=202304
DATA_FOLDERS=("201608_201612" "201701_201712" "201801_201812" "201901_201912" "202001_202012" "202101_202112" "202201_202212" "202301_202304")

CURRENT_FOLDER=$(pwd)

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo "FROM_DATE = "$FROM_DATE
echo "TO_DATE = "$TO_DATE
echo "DATA_FOLDERS = "${DATA_FOLDERS[*]}

DESTINATION_PATH=${CURRENT_FOLDER}/ProcessedDataCombined/${FROM_DATE}_${TO_DATE}
echo "DESTINATION_PATH = "$DESTINATION_PATH

mkdir -p $DESTINATION_PATH

CondoEcRent_FileName=${DESTINATION_PATH}/URA_CondoEcRent_${FROM_DATE}_${TO_DATE}_MP.csv
CondoEcTrans_FileName=${DESTINATION_PATH}/URA_CondoEcTrans_${FROM_DATE}_${TO_DATE}_MP.csv

echo "CondoEcRent_FileName = "$CondoEcRent_FileName
echo "CondoEcTrans_FileName = "$CondoEcTrans_FileName

echo "Start combining ..."

echo 'Building/ Project Name,Street Name,Postal District,Type,No. of Bedroom (for Non-Landed Only),Monthly Rent ($),Floor Area (Sqm) 1,Lease Commencement Date,Yearly Gross Rent($),Floor Area Lower (Sqm),Floor Area Upper (Sqm),Lease Year' > $CondoEcRent_FileName

echo 'Project Name,Street Name,Type,Postal District,Market Segment,Tenure,Type of Sale,No. of Units,Price ($),Area (Sqm),Type of Area,Floor Level,Unit Price ($psm),Date of Sale,Tenure Year,Tenure Length,Floor Area Lower (Sqm),Floor Area Upper (Sqm),Sale Year' > $CondoEcTrans_FileName

for DATA_FOLDER in ${DATA_FOLDERS[@]}
do
    echo "DATA_FOLDER = "$DATA_FOLDER
	grep -h -v 'Project Name' ${CURRENT_FOLDER}/ProcessedData/${DATA_FOLDER}/URA_CondoEcRent_*_MP.csv >> $CondoEcRent_FileName
	grep -h -v 'Project Name' ${CURRENT_FOLDER}/ProcessedData/${DATA_FOLDER}/URA_CondoEcTrans_*_MP.csv >> $CondoEcTrans_FileName
done

echo "Combine completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
