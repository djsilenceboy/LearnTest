#!/bin/bash

DateRange=$1_$2
AppFolder=/f/WorkDJS/RepositoryGit/LearnTest/Python_Test/PyDataMiningSample/com/djs/learn/ura
DataFolder=./RawCsvDataCombined/$DateRange

echo "================================================================================"
echo "Preprocess CSV data ..."
echo "Date: "$(date +"%FT%T%z")
echo "DateRange = "$DateRange
echo "AppFolder = "$AppFolder
echo "DataFolder = "$DataFolder
echo

mkdir -p $DataFolder
echo "Start preprocessing CSV data ..."

echo "------------------------------------------------------------"
python ${AppFolder}/PreprocessCsvData.py -d 0 -i "${DataFolder}/URA_CondoEcTrans_${DateRange}.csv" -o "${DataFolder}/URA_CondoEcTrans_${DateRange}_P.csv"
echo "------------------------------------------------------------"
python ${AppFolder}/PreprocessCsvData.py -d 1 -i "${DataFolder}/URA_CondoEcRent_${DateRange}.csv" -o "${DataFolder}/URA_CondoEcRent_${DateRange}_P.csv"
echo "------------------------------------------------------------"

echo "Preprocess CSV data completed."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
