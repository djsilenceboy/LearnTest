#!/bin/bash

DateRange=$1

CurrentFolder=$(pwd)
CurrentName=$(basename $0)
CsvDataFolder=${CurrentFolder}/RawCsvData/${DateRange}
ProcessedDataFolder=${CurrentFolder}/ProcessedCsvData/${DateRange}

HdbTrans_FileName1=HDB_Trans_${DateRange}_M1.csv
HdbTrans_FileName2=HDB_Trans_${DateRange}_M2.csv

mkdir -p ${ProcessedDataFolder}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CurrentFolder = "$CurrentFolder
echo "CurrentName = "$CurrentName
echo "CsvDataFolder = "$CsvDataFolder
echo "ProcessedDataFolder = "$ProcessedDataFolder
echo 
echo "Start combining (1) ..."

ls -l ${CsvDataFolder}/*.csv | grep '1990\|2000\|2012'

echo 'month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,resale_price' > ${ProcessedDataFolder}/${HdbTrans_FileName1}

grep -h -v 'street_name' --include=${CsvDataFolder}/*{1990,2000,2012}* ${CsvDataFolder}/*.csv >> ${ProcessedDataFolder}/${HdbTrans_FileName1}

echo "Start combining (2) ..."

ls -l ${CsvDataFolder}/*.csv | grep '2015\|2017'

echo 'month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,remaining_lease,resale_price' > ${ProcessedDataFolder}/${HdbTrans_FileName2}

grep -h -v 'street_name' --include=${CsvDataFolder}/*{2015,2017}* ${CsvDataFolder}/*.csv >> ${ProcessedDataFolder}/${HdbTrans_FileName2}

echo "Combine completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
