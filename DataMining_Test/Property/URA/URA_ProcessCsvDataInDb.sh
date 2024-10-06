#!/bin/bash

DateRange=$1_$2
AppFolder=/f/WorkDJS/RepositoryGit/LearnTest/Python_Test/PyDataMiningSample/com/djs/learn/ura
DataFolder=./ProcessedCsvData/$DateRange

echo "================================================================================"
echo "Process CSV data in DB ..."
echo "Date: "$(date +"%FT%T%z")
echo "DateRange = "$DateRange
echo "AppFolder = "$AppFolder
echo "DataFolder = "$DataFolder
echo

mkdir -p $DataFolder
echo "Start processing CSV data in DB ..."

echo "------------------------------------------------------------"
python ${AppFolder}/ProcessCsvDataInDb.py -t "${DataFolder}/URA_CondoEcTrans_${DateRange}_P.csv" -r "${DataFolder}/URA_CondoEcRent_${DateRange}_P.csv" -d "${DataFolder}/URA_CondoEcTransRent.db" -o "${DataFolder}/URA_CondoEcResults_${DateRange}"
echo "------------------------------------------------------------"

echo "Process CSV data in DB completed."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
