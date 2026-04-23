#!/bin/bash

DateRange=$1

AppFolder=/f/WorkDJS/RepositoryGit/LearnTest/Python_Test/PyDataMiningSample/com/djs/learn/hdb
DataFolder=./ProcessedCsvData/$DateRange

echo "================================================================================"
echo "Process data in DB ..."
echo "Date: "$(date +"%FT%T%z")
echo "DateRange = "$DateRange
echo "AppFolder = "$AppFolder
echo "DataFolder = "$DataFolder
echo

mkdir -p $DataFolder
echo "Start processing data in DB ..."

echo ------------------------------------------------------------
python ${AppFolder}/ProcessDataInDb2.py -t "${DataFolder}/HDB_Trans_${DateRange}_MP.csv" -d "${DataFolder}/HDB_Trans.db" -o "${DataFolder}/HDB_Results_${DateRange}_"
echo ------------------------------------------------------------

echo "Process data in DB completed."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
