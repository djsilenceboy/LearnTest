#!/bin/bash

RawFileFolder=$1
CombinedFileFolder=$2
TransFileMainName=$3
RentFileMainName=$4

echo "------------------------------------------------------------"
echo "RawFileFolder = "$RawFileFolder
echo "CombinedFileFolder = "$CombinedFileFolder
echo "TransFileMainName = "$TransFileMainName
echo "RentFileMainName = "$RentFileMainName

CombinedTransFile=${CombinedFileFolder}/${TransFileMainName}.csv
CombinedRentFile=${CombinedFileFolder}/${RentFileMainName}.csv

echo "CombinedTransFile = "$CombinedTransFile
echo "CombinedRentFile = "$CombinedRentFile

echo 'Project Name,Transacted Price ($),Sale Date,Street Name,Type of Sale,Type of Area,Area (SQM),Unit Price ($ PSM),Nett Price($),Property Type,Number of Units,Tenure,Postal District,Market Segment,Floor Level' > $CombinedTransFile

echo 'Project Name,Street Name,Postal District,Property Type,No of Bedroom,Monthly Rent ($),Floor Area (SQM),Floor Area (SQFT),Lease Commencement Date' > $CombinedRentFile

grep -h -v 'Project Name' ${RawFileFolder}/${TransFileMainName}_*.csv >> $CombinedTransFile
grep -h -v 'Project Name' ${RawFileFolder}/${RentFileMainName}_*.csv >> $CombinedRentFile

echo "------------------------------------------------------------"
