#!/bin/bash

DateRange=$1

AppFolder=/f/WorkDJS/RepositoryGit/LearnTest/Python_Test/PyDataMiningSample/com/djs/learn/hdb
DataFolder=./ProcessedCsvData/$DateRange
HdbTrans_FileName=HDB_Trans_${DateRange}_MP.csv

echo "================================================================================"
echo "Preprocess CSV data ..."
echo "Date: "$(date +"%FT%T%z")
echo "DateRange = "$DateRange
echo "AppFolder = "$AppFolder
echo "DataFolder = "$DataFolder
echo

mkdir -p $DataFolder
echo "Start preprocessing data ..."

echo ------------------------------------------------------------
python ${AppFolder}/PreprocessData2.py -d 0 -i "${DataFolder}/HDB_Trans_${DateRange}_M1.csv" -o "${DataFolder}/HDB_Trans_${DateRange}_MR1.csv"
python ${AppFolder}/PreprocessData2.py -d 0 -i "${DataFolder}/HDB_Trans_${DateRange}_M2.csv" -o "${DataFolder}/HDB_Trans_${DateRange}_MR2.csv"
echo ------------------------------------------------------------

echo "Start combining ..."

ls -l ${DataFolder}/HDB_Trans_${DateRange}_MR*.csv

echo 'month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,remaining_lease,resale_price,floor_area_lower_sqm,unit_price,resale_year' > ${DataFolder}/${HdbTrans_FileName}

grep -h -v 'street_name' ${DataFolder}/HDB_Trans_${DateRange}_MR*.csv >> ${DataFolder}/${HdbTrans_FileName}

rm -rf ${DataFolder}/HDB_Trans_${DateRange}_MR*.csv
ls -l ${DataFolder}/${HdbTrans_FileName}

echo ------------------------------------------------------------

echo "Preprocess data completed."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
