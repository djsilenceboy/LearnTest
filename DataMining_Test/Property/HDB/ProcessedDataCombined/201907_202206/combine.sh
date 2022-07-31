#!/bin/bash

FROM_DATE=201907
TO_DATE=202206

HDBTrans_FileName=HDB_Trans_${FROM_DATE}_${TO_DATE}_MP.csv

DATA_MAIN_FOLDER=../../ProcessedData
DATA_TEMP_FOLDER=./OriginalData

rm -f ./*.csv ./*.db
rm -rf $DATA_TEMP_FOLDER

echo "============================================================"
env | sort

echo "============================================================"
mkdir -p $DATA_TEMP_FOLDER

find $DATA_MAIN_FOLDER -name HDB_*_MP.csv -exec cp {} $DATA_TEMP_FOLDER \;
ls -l $DATA_TEMP_FOLDER

echo "============================================================"

echo 'Block / Nearby Amenities,Street Name,Storey,Floor Area (sqm) /Flat Model,Lease Commence Date,Remaining Lease,Resale Price,Resale Registration Date,Flat Model,Floor Area (sqm),Floor Area Lower (sqm),Unit Price,Resale Year' > $HDBTrans_FileName

grep -v "Price\|Jun 2019\|Jun 2020" $DATA_TEMP_FOLDER/HDB_Trans_20200627_12m_MP.csv >> $HDBTrans_FileName
grep -v "Price\|May 2020\|May 2021" $DATA_TEMP_FOLDER/HDB_Trans_20210511_12m_MP.csv >> $HDBTrans_FileName
grep "May 2021\|Jun 2021\|Jul 2021" $DATA_TEMP_FOLDER/HDB_Trans_20210811_12m_MP.csv >> $HDBTrans_FileName
grep -v "Price\|Jul 2021\|Jul 2022" $DATA_TEMP_FOLDER/HDB_Trans_20220710_12m_MP.csv >> $HDBTrans_FileName

echo "============================================================"

rm -rf $DATA_TEMP_FOLDER
ls -l
