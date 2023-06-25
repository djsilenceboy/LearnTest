#!/bin/bash

FromYear=$1
FromMonth=$2
ToYear=$3
ToMonth=$4

CurrentFolder=$(pwd)
CurrentFileName=$(basename $0)
DateRange="${FromYear}${FromMonth}_${ToYear}${ToMonth}"
TmpFolder=${CurrentFolder}/tmp
LogFile=${CurrentFileName}_${DateRange}.log

mkdir -p ${TmpFolder}

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "CurrentFolder = "$CurrentFolder
echo "CurrentFileName = "$CurrentFileName
echo "FromYear = "$FromYear
echo "FromMonth = "$FromMonth
echo "ToYear = "$ToYear
echo "ToMonth = "$ToMonth
echo "DateRange = "$DateRange
echo "TmpFolder = "$TmpFolder
echo "LogFile = "$LogFile

echo 
echo "Start downloading ..."

${CurrentFolder}/URA_DownloadCsvData_Run.sh $FromYear $FromMonth $ToYear $ToMonth |& tee ${TmpFolder}/${LogFile}

echo "Download completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
