================================================================================
URA property transaction / rental info

https://www.ura.gov.sg
https://www.ura.gov.sg/realEstateIIWeb/transaction/search.action
------------------------------------------------------------
Downlaod following data as CSV with square meter:
Private Residential Property Transactions
Rental Contracts of Private Residential Properties
------------------------------------------------------------
For "Private Residential Property Transactions",
Select any Property Type: "Apartments & Condominiums" and "Executive Condominiums"
Select Type of Sale: "Resale"
------------------------------------------------------------
For "Rental Contracts of Private Residential Properties",
Select any Property Type: "Non-Landed Housing Development" and "Executive Condominiums"
================================================================================

================================================================================
Download HTML files
================================================================================
Update "URA_DownloadHtmlData_Run.sh" script with downloading parameters.

Download all HTML files (use bash) by:
./URA_DownloadHtmlData_Run.sh
------------------------------------------------------------
Generated files (sample):

URA_CondoEcRent_201706-202005_A.sh
URA_CondoEcRent_201706-202005_B.sh
URA_CondoEcTrans_201706-202005_A.sh
URA_CondoEcTrans_201706-202005_B.sh

Downloaded files:

URA_CondoEcRent_201706-202005_A1.html
......
URA_CondoEcRent_201706-202005_A6.html
URA_CondoEcRent_201706-202005_B1.html
......
URA_CondoEcRent_201706-202005_B6.html
URA_CondoEcTrans_201706-202005_A1.html
......
URA_CondoEcTrans_201706-202005_A6.html
URA_CondoEcTrans_201706-202005_B1.html
......
URA_CondoEcTrans_201706-202005_B6.html
================================================================================

================================================================================
Downloaded file naming
------------------------------------------------------------
URA_CondoEcTrans_201705-202004_AB1.csv: Condo transaction (A) Batch 1 (B1)
URA_CondoEcTrans_201705-202004_AB2.csv: Condo transaction (A) Batch 2 (B2)
......
URA_CondoEcTrans_201705-202004_BB1.csv: EC    transaction (B) Batch 1 (B1)
URA_CondoEcTrans_201705-202004_BB2.csv: EC    transaction (B) Batch 2 (B2)
......
URA_CondoEcRent_201705-202004_AB1.csv: Condo rent (A) Batch 1 (B1)
URA_CondoEcRent_201705-202004_AB2.csv: Condo rent (A) Batch 2 (B2)
......
URA_CondoEcRent_201705-202004_BB1.csv: EC    rent (B) Batch 1 (B1)
URA_CondoEcRent_201705-202004_BB2.csv: EC    rent (B) Batch 2 (B2)
......
================================================================================

================================================================================
Process downloaded files
================================================================================
Copy all downladed files into folder

.\RawData_201705_202004
------------------------------------------------------------
Generate header line files

Extract header line from:
URA_CondoEcTrans_201705-202004_AB1
to:
URA_CondoEcTrans_201705-202004_H.csv

Extract header line from:
URA_CondoEcRent_201705-202004_AB1
to:
URA_CondoEcRent_201705-202004_H.csv
------------------------------------------------------------
Merge all data files (use bash)

./URA_MergeData.sh ./RawData_201705_202004/URA_CondoEcTrans_201705-202004 ./ProcessedData_201705_202004/URA_CondoEcTrans_201705-202004
./URA_MergeData.sh ./RawData_201705_202004/URA_CondoEcRent_201705-202004 ./ProcessedData_201705_202004/URA_CondoEcRent_201705-202004

Generated files:
./ProcessedData_201705_202004/URA_CondoEcTrans_201705-202004_M.csv
./ProcessedData_201705_202004/URA_CondoEcRent_201705-202004_M.csv
------------------------------------------------------------
Preprocess all data files

(\Python_Test\PyDataMiningSample\com\djs\learn\test\ura\)
python PreprocessData.py -d 0 -i ".\ProcessedData_201705_202004\URA_CondoEcTrans_201705-202004_M.csv" -o ".\ProcessedData_201705_202004\URA_CondoEcTrans_201705-202004_MP.csv"
python PreprocessData.py -d 1 -i ".\ProcessedData_201705_202004\URA_CondoEcRent_201705-202004_M.csv" -o ".\ProcessedData_201705_202004\URA_CondoEcRent_201705-202004_MP.csv"

Generated files:
.\ProcessedData_201705_202004\URA_CondoEcTrans_201705-202004_MP.csv
.\ProcessedData_201705_202004\URA_CondoEcRent_201705-202004_MP.csv
================================================================================

================================================================================
During preprocess by Python
================================================================================
For "Private Residential Property Transactions", remain following fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
Project Name,Street Name,Type,Postal District,Market Segment,Tenure,Type of Sale,No. of Units,Price ($),Area (Sqm),Type of Area,Floor Level,Unit Price ($psm),Date of Sale,Tenure Year,Tenure Length,Floor Area Lower (sq m),Floor Area Upper (sq m),Sale Year
VICTORY HEIGHTS,KIM KEAT ROAD,Apartment,12,RCR,Freehold,Resale,1,998888,104,Strata,06 to 10,9605,Apr-2020,0,9999,100,110,2020
PUBLIC MANSION,BALESTIER ROAD,Apartment,12,RCR,Freehold,Resale,1,1900000,235,Strata,06 to 10,8085,Apr-2020,0,9999,230,240,2020
PARC SOMME,SOMME ROAD,Apartment,08,RCR,99 yrs lease commencing from 2008,Resale,1,518000,32,Strata,01 to 05,16188,Apr-2020,2008,99,30,40,2020
PARC SOMME,SOMME ROAD,Apartment,08,RCR,99 yrs lease commencing from 2008,Resale,1,570000,33,Strata,06 to 10,17273,Apr-2020,2008,99,30,40,2020
BEACON HEIGHTS,MAR THOMA ROAD,Condominium,12,RCR,999 yrs lease commencing from 1882,Resale,1,1330000,103,Strata,21 to 25,12913,Apr-2020,1882,999,100,110,2020
------------------------------------------------------------
Note that,
1. Removed fields "S/N", "Nett Price ($)".

2. Add fields (excel formula):
A1                      = Tenure
A2                      = Area (Sqm)
A3                      = Date of Sale
Tenure Year             =IF(A1 = "Freehold", "0", RIGHT(A1,4))
Tenure Length           =IF(A1 = "Freehold", "9999", LEFT(A1, FIND("yrs", A1) -1))
Floor Area Lower (Sqm)  =ROUNDDOWN(A2/10, 0)*10
Floor Area Upper (Sqm)  =ROUNDUP(A2/10, 0)*10
Sale Year               =RIGHT(A3,4)
================================================================================
For "Rental Contracts of Private Residential Properties", remain following fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
Building/Project Name,Street Name,Postal District,Type,No. of Bedroom(for Non-Landed Only),Monthly Gross Rent($),Floor Area (sq m),Lease Commencement Date,Yearly Gross Rent($),Floor Area Lower (sq m),Floor Area Upper (sq m),Lease Year
FORTE SUITES,MERGUI ROAD,08,Non-landed Properties,2,3100,100 to 110,Apr-2020,37200,100,110,2020
BOTANNIA,WEST COAST PARK,05,Non-landed Properties,4,4500,140 to 150,Apr-2020,54000,140,150,2020
PARC RIVIERA,WEST COAST VALE,05,Non-landed Properties,2,2450,60 to 70,Apr-2020,29400,60,70,2020
ISLAND VIEW,JALAN MAT JAMBOL,05,Non-landed Properties,3,6000,>300,Apr-2020,72000,300,300,2020
ISLAND VIEW,JALAN MAT JAMBOL,05,Non-landed Properties,3,8150,>300,Apr-2020,97800,300,300,2020
------------------------------------------------------------
Note that,
1. Removed fields "S/N".

2. Add fields (excel formula):
A1                      = Monthly Gross Rent($)
A2                      = Floor Area (sq m)
A3                      = Lease Commencement Date
Yearly Gross Rent($)    = A1 * 12
Floor Area Lower (sq m) =IF(LEFT(A2,1) <> ">", LEFT(A2, FIND("to", A2) -1), MID(A2,2,LEN(A2)))
Floor Area Upper (sq m) =IF(LEFT(A2,1) <> ">", RIGHT(A2, LEN(A2) - FIND("to", A2) - 2), MID(A2,2,LEN(A2)))
Lease Year              =RIGHT(A3,4)
================================================================================

================================================================================
MySQL
================================================================================
MySQL tables
------------------------------------------------------------
Create following tables and indexes inside "URA_mysql_tb_ura_data.sql".
------------------------------------------------------------
Import "Private Residential Property Transactions" CSV for URA_CONDOEC_TRANS_HIST table.
Import "Rental Contracts of Private Residential Properties" CSV for URA_CONDOEC_RENT_HIST table.
------------------------------------------------------------
Run query SQL scripts from "URA_mysql_scripts.txt".
================================================================================

================================================================================
SQLite
================================================================================
Process pre-processed data files

(\Python_Test\PyDataMiningSample\com\djs\learn\test\ura\)
python ProcessDataInDb.py -t ".\ProcessedData_201705_202004\URA_CondoEcTrans_201705-202004_MP.csv" -r ".\ProcessedData_201705_202004\URA_CondoEcRent_201705-202004_MP.csv" -d ".\ProcessedData_201705_202004\URA_CondoEcTransRent.db" -o ".\ProcessedData_201705_202004\URA_CondoEcResults_201705-202004_"

Generated files:
.\ProcessedData_201705_202004\URA_CondoEcTransRent.db
.\ProcessedData_201705_202004\URA_CondoEcResults_201705-202004_PriceRentRatio.csv
.\ProcessedData_201705_202004\URA_CondoEcResults_201705-202004_RentYearlyPrice.csv
.\ProcessedData_201705_202004\URA_CondoEcResults_201705-202004_TransYearlyPrice.csv
================================================================================
