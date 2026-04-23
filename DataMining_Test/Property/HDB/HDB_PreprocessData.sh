#!/bin/bash

DateRange=$1

AppFolder=/f/WorkDJS/RepositoryGit/LearnTest/Python_Test/PyDataMiningSample/com/djs/learn/hdb
DataFolder=./ProcessedCsvData/$DateRange

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
python ${AppFolder}/PreprocessData2.py -d 0 -i "${DataFolder}/HDB_Trans_${DateRange}_M.csv" -o "${DataFolder}/HDB_Trans_${DateRange}_MP.csv"
echo ------------------------------------------------------------

echo "Preprocess data completed."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
