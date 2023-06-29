#!/bin/bash

FromDate=$1
ToDate=$2

CurrentFolder=$(pwd)
DateRange="${FromDate}_${ToDate}"
RawCombinedFileFolder="${CurrentFolder}/RawCsvDataCombined"
ProcessedCsvFileFolder="${CurrentFolder}/ProcessedCsvData/${DateRange}"

TransFileMainName="URA_CondoEcTrans"
RentFileMainName="URA_CondoEcRent"
FinalTransFileName=${ProcessedCsvFileFolder}/${TransFileMainName}_${DateRange}_P.csv
FinalRentFileName=${ProcessedCsvFileFolder}/${RentFileMainName}_${DateRange}_P.csv

echo "================================================================================"
echo "Combine preprocessed CSV data ..."
echo "Date: "$(date +"%FT%T%z")

echo "CurrentFolder = "$CurrentFolder
echo "FromDate = "$FromDate
echo "ToDate = "$ToDate
echo "DateRange = "$DateRange
echo "RawCombinedFileFolder = "$RawCombinedFileFolder
echo "ProcessedCsvFileFolder = "$ProcessedCsvFileFolder
echo "TransFileMainName = "$TransFileMainName
echo "RentFileMainName = "$RentFileMainName
echo "FinalTransFileName = "$FinalTransFileName
echo "FinalRentFileName = "$FinalRentFileName
echo

mkdir -p ${ProcessedCsvFileFolder}

echo 'Project Name,Transacted Price ($),Sale Date,Street Name,Type of Sale,Type of Area,Area (SQM),Unit Price ($ PSM),Property Type,Number of Units,Tenure,Postal District,Market Segment,Floor Level,Tenure Year,Tenure Length,Floor Area Lower (SQM),Floor Area Upper (SQM),Sale Year' > $FinalTransFileName

echo 'Project Name,Street Name,Postal District,Property Type,No of Bedroom,Monthly Rent ($),Floor Area (SQM),Lease Commencement Date,Yearly Rent ($),Floor Area Lower (SQM),Floor Area Upper (SQM),Lease Year' > $FinalRentFileName

grep -h -v 'Project Name' ${RawCombinedFileFolder}/*/${TransFileMainName}_*_P.csv >> $FinalTransFileName
grep -h -v 'Project Name' ${RawCombinedFileFolder}/*/${RentFileMainName}_*_P.csv >> $FinalRentFileName


echo "Combine preprocessed CSV data completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
