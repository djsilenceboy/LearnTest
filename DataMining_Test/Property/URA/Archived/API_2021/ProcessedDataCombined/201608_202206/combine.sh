#!/bin/bash

FROM_DATE=201608
TO_DATE=202206

CondoEcRent_FileName=URA_CondoEcRent_${FROM_DATE}_${TO_DATE}_MP.csv
CondoEcTrans_FileName=URA_CondoEcTrans_${FROM_DATE}_${TO_DATE}_MP.csv

DATA_MAIN_FOLDER=../../ProcessedData
DATA_TEMP_FOLDER=./OriginalData

rm -f ./*.csv ./*.db
rm -rf $DATA_TEMP_FOLDER

echo "============================================================"
env | sort

echo "============================================================"
mkdir -p $DATA_TEMP_FOLDER

find $DATA_MAIN_FOLDER -name URA_*_MP.csv -exec cp {} $DATA_TEMP_FOLDER \;
ls -l $DATA_TEMP_FOLDER

echo "============================================================"

echo 'Building/ Project Name,Street Name,Postal District,Type,No. of Bedroom (for Non-Landed Only),Monthly Rent ($),Floor Area (Sqm) 1,Lease Commencement Date,Yearly Gross Rent($),Floor Area Lower (Sqm),Floor Area Upper (Sqm),Lease Year' > $CondoEcRent_FileName

grep -h -v 'Project Name' ${DATA_TEMP_FOLDER}/URA_CondoEcRent_*.csv >> $CondoEcRent_FileName

echo 'Project Name,Street Name,Type,Postal District,Market Segment,Tenure,Type of Sale,No. of Units,Price ($),Area (Sqm),Type of Area,Floor Level,Unit Price ($psm),Date of Sale,Tenure Year,Tenure Length,Floor Area Lower (Sqm),Floor Area Upper (Sqm),Sale Year' > $CondoEcTrans_FileName

grep -h -v 'Project Name' ${DATA_TEMP_FOLDER}/URA_CondoEcTrans_*.csv >> $CondoEcTrans_FileName

echo "============================================================"

rm -rf $DATA_TEMP_FOLDER
ls -l
