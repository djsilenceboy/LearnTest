================================================================================
HDB info
------------------------------------------------------------
https://services2.hdb.gov.sg/webapp/BB33RTIS/BB33PReslTrans.jsp
https://services2.hdb.gov.sg/webapp/FI10AWESVCLIST/FI10SEServiceList
================================================================================

================================================================================
Download HTML files
================================================================================
Update "HDB_DownloadHtmlData_Batch.sh" script with downloading parameters.

Download all HTML files (use bash) by:
./HDB_DownloadHtmlData_Batch.sh
------------------------------------------------------------
Downloaded file folder (sample):

.\HtmlData_20200627_12m
----------------------------------------
Downloaded files:

HDB_Trans_20200627_12m_01_1.html
......
HDB_Trans_20200627_12m_01_27.html
HDB_Trans_20200627_12m_02_1.html
......
HDB_Trans_20200627_12m_02_27.html
HDB_Trans_20200627_12m_03_1.html
......
HDB_Trans_20200627_12m_08_27.html
================================================================================

================================================================================
All steps in one batch
------------------------------------------------------------
Convert HTML data to CSV
Preprocess CSV files
Process pre-processed data files by SQLite
================================================================================
Update "HDB_ProcessData_Batch.bat" script with parameters.

Run:

HDB_ProcessData_Batch.bat
================================================================================
Following are separated steps.
================================================================================

================================================================================
Convert HTML data to CSV
================================================================================
Run:

HDB_ConvertHtmlToRawData.bat 20200627_12m
------------------------------------------------------------
Generated files:

.\ProcessedData_20200627_12m\HDB_Trans_20200627_12m_M.csv
================================================================================

================================================================================
Preprocess CSV files
================================================================================
Run:

HDB_PreprocessData.bat 20200627_12m
------------------------------------------------------------
Generated files:

.\ProcessedData_20200627_12m\HDB_Trans_20200627_12m_MP.csv
================================================================================
During preprocess by Python
================================================================================
For "Private Residential Property Transactions", remain following fields.
(Head line with 5 sample data lines.)
------------------------------------------------------------
Block / Nearby Amenities,Street Name,Storey,Floor Area (sqm) /Flat Model,Lease Commence Date,Remaining Lease,Resale Price,Resale Registration Date,Flat Model,Floor Area (sqm),Floor Area Lower (sqm),Unit Price,Resale Year
10,Jln Kukoh,10 to 12,53/Improved,1971,50 years 2 months,200000,Jun 2020,Improved,53,50,3773,2020
9,Jln Kukoh,10 to 12,55/Improved,1982,60 years 10 months,222000,Mar 2020,Improved,55,50,4036,2020
527B,Pasir Ris St 51,04 to 06,47/Model A,2015,94 years 3 months,250000,Jun 2020,Model A,47,40,5319,2020
527B,Pasir Ris St 51,10 to 12,47/Model A,2015,94 years 3 months,265000,Jun 2020,Model A,47,40,5638,2020
------------------------------------------------------------
Note that,
1. Add fields (excel formula):
A4                      = Floor Area (sqm) /Flat Model
A6                      = Resale Price
A7                      = Resale Registration Date
Flat Model              =MID(A1, FIND("/", A1) +1)
Floor Area (sqm)        =LEFT(A1, FIND("/", A1) -1)
A9                      = Floor Area (sqm)
Floor Area Lower (sqm)  =ROUNDDOWN(A9/10, 0)*10
Unit Price              =ROUNDDOWN(A6/A9, 0)
Resale Year             =RIGHT(A1, 4)
================================================================================

================================================================================
MySQL
================================================================================
MySQL tables
------------------------------------------------------------
Create following tables and indexes inside "HDB_mysql_tb_ura_data.sql".
------------------------------------------------------------
Import CSV for HDB_TRANS_HIST table.
------------------------------------------------------------
Run query SQL scripts from "HDB_mysql_scripts.txt".
================================================================================

================================================================================
Process pre-processed data files by SQLite
================================================================================
Run:

HDB_ProcessDataInDb.bat 20200627_12m
------------------------------------------------------------
Generated files:

.\ProcessedData_20200627_12m\HDB_TransRent.db
.\ProcessedData_20200627_12m\HDB_Results_20200627_12m_TransPrice.csv
.\ProcessedData_20200627_12m\HDB_Results_20200627_12m_TransPriceB.csv
================================================================================
