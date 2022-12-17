================================================================================
HDB info
------------------------------------------------------------
https://data.gov.sg/dataset/resale-flat-prices
------------------------------------------------------------
Click "Download" to get all csv in zip.
================================================================================

================================================================================
Combine needed CSV files to one file.
------------------------------------------------------------
Update "HDB_CombineCsvData.sh.sh".

In Git Bash, run:
./HDB_CombineCsvData.sh.sh
------------------------------------------------------------
Generated files:

.\ProcessedDataCombined\20221217\HDB_Trans_20221217_M.csv
================================================================================

================================================================================
All steps in one batch
------------------------------------------------------------
Preprocess CSV files
Process pre-processed data files by SQLite
================================================================================
Update "HDB_ProcessData_Batch.bat" script with parameters.

In Windows Prompt, run:
HDB_ProcessDataCombined_Batch.bat
================================================================================
Following are separated steps.
================================================================================

================================================================================
Preprocess CSV files
================================================================================
In Windows Prompt, run:
HDB_PreprocessData.bat 20221217 "Combined"
------------------------------------------------------------
Generated files:

.\ProcessedDataCombined\20221217\HDB_Trans_20221217_MP.csv
================================================================================
During preprocess by Python
================================================================================
Remain orginal fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
month,town,flat_type,block,street_name,storey_range,floor_area_sqm,flat_model,lease_commence_date,remaining_lease,resale_price,floor_area_lower_sqm,unit_price,resale_year
2015-01,ANG MO KIO,3 ROOM,174,ANG MO KIO AVE 4,07 TO 09,60,Improved,1986,70,255000,60,4250,2015
2015-01,ANG MO KIO,3 ROOM,541,ANG MO KIO AVE 10,01 TO 03,68,New Generation,1981,65,275000,60,4044,2015
2015-01,ANG MO KIO,3 ROOM,163,ANG MO KIO AVE 4,01 TO 03,69,New Generation,1980,64,285000,60,4130,2015
2015-01,ANG MO KIO,3 ROOM,446,ANG MO KIO AVE 10,01 TO 03,68,New Generation,1979,63,290000,60,4264,2015
2015-01,ANG MO KIO,3 ROOM,557,ANG MO KIO AVE 10,07 TO 09,68,New Generation,1980,64,290000,60,4264,2015
------------------------------------------------------------
Note that,
1. Add fields (excel formula):
A1                      = Resale Date
A7                      = Floor Area (sqm)
A11                     = Resale Price
Floor Area Lower (sqm)  =ROUNDDOWN(A7/10, 0)*10
Unit Price              =ROUNDDOWN(A11/A7, 0)
Resale Year             =RIGHT(A1, 4)
================================================================================

================================================================================
MySQL
================================================================================
MySQL tables
------------------------------------------------------------
Create following tables and indexes inside "HDB_mysql_tb.sql".
------------------------------------------------------------
Import CSV for HDB_TRANS_HIST table.
------------------------------------------------------------
Run query SQL scripts from "HDB_mysql_scripts.txt".
================================================================================

================================================================================
Process pre-processed data files by SQLite
================================================================================
Run:

HDB_ProcessDataInDb.bat 20221217 "Combined"
------------------------------------------------------------
Generated files:

.\ProcessedDataCombined\20221217\HDB_TransRent.db
.\ProcessedDataCombined\20221217\HDB_Results_20221217_TransPriceA.csv
.\ProcessedDataCombined\20221217\HDB_Results_20221217_TransPriceB.csv
.\ProcessedDataCombined\20221217\HDB_Results_20221217_TransPriceC.csv
.\ProcessedDataCombined\20221217\HDB_Results_20221217_TransPriceD.csv
================================================================================
