#!/bin/bash

DateRange=$1

CurrentFolder=$(pwd)
CurrentName=$(basename $0)
CsvDataFolder=${CurrentFolder}/RawCsvData/${DateRange}
ProcessedDataFolder=${CurrentFolder}/ProcessedCsvData/${DateRange}
TempFolder=${CurrentFolder}/tmp

HdbTrans_FileName=HDB_Trans_${DateRange}_M.csv

mkdir -p ${TempFolder}
mkdir -p ${ProcessedDataFolder}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CurrentFolder = "$CurrentFolder
echo "CurrentName = "$CurrentName
echo "RawCsvData_FOLDER = "$CsvDataFolder
echo "ProcessedDataFolder = "$ProcessedDataFolder
echo 
echo "Start combining ..."

ls -l ${CsvDataFolder}/*.csv | grep '2015\|2017'

echo 'month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,remaining_lease,resale_price' > ${ProcessedDataFolder}/${HdbTrans_FileName}

grep -h -v 'street_name' --include=${CsvDataFolder}/*{2015,2017}* ${CsvDataFolder}/*.csv >> ${ProcessedDataFolder}/${HdbTrans_FileName}

echo "Combine completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
