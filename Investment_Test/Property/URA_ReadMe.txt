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
Downloaded file folder:

.\HtmlData_201706_202005
----------------------------------------
Generated files:

URA_CondoEcRent_201706-202005_A.sh
URA_CondoEcRent_201706-202005_B.sh
URA_CondoEcTrans_201706-202005_A.sh
URA_CondoEcTrans_201706-202005_B.sh
----------------------------------------
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
Convert HTML data to CSV
================================================================================
Run:

(\Python_Test\PyDataMiningSample\com\djs\learn\test\ura\)
python ConvertHtmlToRawData.py -f ".\HtmlData_201706_202005" -i "URA_CondoEcTrans_201706-202005_" -o ".\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_M.csv"
python ConvertHtmlToRawData.py -f ".\HtmlData_201706_202005" -i "URA_CondoEcRent_201706-202005_" -o ".\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_M.csv"
------------------------------------------------------------
Generated files:

.\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_M.csv
.\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_M.csv
================================================================================

================================================================================
Preprocess CSV files
================================================================================
Run:

(\Python_Test\PyDataMiningSample\com\djs\learn\test\ura\)
python PreprocessData.py -d 0 -i ".\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_M.csv" -o ".\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_MP.csv"
python PreprocessData.py -d 1 -i ".\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_M.csv" -o ".\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_MP.csv"
------------------------------------------------------------
Generated files:

.\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_MP.csv
.\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_MP.csv
================================================================================
During preprocess by Python
================================================================================
For "Private Residential Property Transactions", remain following fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
Project Name,Street Name,Type,Postal District,Market Segment,Tenure,Type of Sale,No. of Units,Price ($),Nett Price ($),Area (Sqm),Type of Area,Floor Level,Unit Price ($psm),Date of Sale
GARDENVILLE,WALSHE ROAD,Condominium,10,CCR,Freehold,Resale,1,4510000,,247,Strata,01 to 05,18259,May-20
DUKES RESIDENCE,DUKE'S ROAD,Apartment,10,CCR,Freehold,Resale,1,2300000,,107,Strata,01 to 05,21495,May-20
THE SIENA,TAN KIM CHENG ROAD,Apartment,10,CCR,99 yrs lease commencing from 2013,Resale,1,1280000,,73,Strata,01 to 05,17534,Apr-20
VALLEY PARK,RIVER VALLEY ROAD,Condominium,10,CCR,999 yrs lease commencing from 1877,Resale,1,2750000,,158,Strata,16 to 20,17405,Apr-20
SPRING GROVE,GRANGE ROAD,Condominium,10,CCR,99 yrs lease commencing from 1991,Resale,1,1550000,,94,Strata,06 to 10,16489,Apr-20
DARBY PARK,ORANGE GROVE ROAD,Apartment,10,CCR,99 yrs lease commencing from 1993,Resale,55,92714566,,3373.4,Land,01 to 05,27484,Nov-18
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
Sale Year               ="20" & RIGHT(A3,2)
================================================================================
For "Rental Contracts of Private Residential Properties", remain following fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
Building/ Project Name,Street Name,Postal District,Type,No. of Bedroom (for Non-Landed Only),Monthly Rent ($),Floor Area (Sqm) 1,Lease Commencement Date
DUCHESS CREST,DUCHESS AVENUE,10,Non-landed Properties,2,3300,90 to 100,Apr-20
RIDGEWOOD,MOUNT SINAI DRIVE,10,Non-landed Properties,3,3100,120 to 130,Apr-20
BISHOPSGATE RESIDENCES,BISHOPSGATE,10,Non-landed Properties,5,15000,290 to 300,Apr-20
NASSIM PARK RESIDENCES,NASSIM ROAD,10,Non-landed Properties,4,30000,>300,Apr-20
ORCHARD BEL AIR,ORCHARD BOULEVARD,10,Non-landed Properties,0,8800,>300,Apr-20
------------------------------------------------------------
Note that,
1. Removed fields "S/N".

2. Add fields (excel formula):
A1                      = Monthly Rent($)
A2                      = Floor Area (Sqm) 1
A3                      = Lease Commencement Date
Yearly Gross Rent($)    = A1 * 12
Floor Area Lower (sq m) =IF(LEFT(A2,1) <> ">", LEFT(A2, FIND("to", A2) -1), MID(A2,2,LEN(A2)))
Floor Area Upper (sq m) =IF(LEFT(A2,1) <> ">", RIGHT(A2, LEN(A2) - FIND("to", A2) - 2), MID(A2,2,LEN(A2)))
Lease Year              ="20" & RIGHT(A3,2)
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
Process pre-processed data files by SQLite
================================================================================
Run:

(\Python_Test\PyDataMiningSample\com\djs\learn\test\ura\)
python ProcessDataInDb.py -t ".\ProcessedData_201706_202005\URA_CondoEcTrans_201706-202005_MP.csv" -r ".\ProcessedData_201706_202005\URA_CondoEcRent_201706-202005_MP.csv" -d ".\ProcessedData_201706_202005\URA_CondoEcTransRent.db" -o ".\ProcessedData_201706_202005\URA_CondoEcResults_201706-202005_"
------------------------------------------------------------
Generated files:

.\ProcessedData_201706_202005\URA_CondoEcTransRent.db
.\ProcessedData_201706_202005\URA_CondoEcResults_201706-202005_PriceRentRatio.csv
.\ProcessedData_201706_202005\URA_CondoEcResults_201706-202005_RentYearlyPrice.csv
.\ProcessedData_201706_202005\URA_CondoEcResults_201706-202005_TransYearlyPrice.csv
================================================================================
