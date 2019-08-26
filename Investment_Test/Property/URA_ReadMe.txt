================================================================================
URA property transaction / rental info

https://www.ura.gov.sg
https://www.ura.gov.sg/realEstateIIWeb/transaction/search.action
================================================================================
Downlaod following data as CSV with square meter:
Private Residential Property Transactions
Rental Contracts of Private Residential Properties
================================================================================

================================================================================
For "Private Residential Property Transactions", remain following fields.
(Head line with 2 sample data lines.)
------------------------------------------------------------
Project Name,Street Name,Type,Postal District,Market Segment,Tenure,Type of Sale,No. of Units,Price ($),Area (Sqm),Type of Area,Floor Level,Unit Price ($psm),Date of Sale,Floor Area Lower (Sqm),Floor Area Upper (Sqm)
BURLINGTON SQUARE,BENCOOLEN STREET,Apartment,7,RCR,99 yrs lease commencing from 1996,Resale,1,1070000,77,Strata,06 to 10,13896,May-19,70,80
BURLINGTON SQUARE,BENCOOLEN STREET,Apartment,7,RCR,99 yrs lease commencing from 1996,Resale,1,1320000,99,Strata,16 to 20,13333,Jun-19,90,100
------------------------------------------------------------
Note that, following 3 fields are generated from other original fields by excel formula.

J2                      = Area (Sqm)
Floor Area Lower (Sqm)  =ROUNDDOWN(J2/10, 0)*10
Floor Area Upper (Sqm)  =ROUNDUP(J2/10, 0)*10
================================================================================
For "Rental Contracts of Private Residential Properties", remain following fields.
(Head line with 3 sample data lines.)
------------------------------------------------------------
Building/Project,Street,Postal District,Type,No. of Bedroom(for Non-Landed Only),Monthly Gross Rent($),Floor Area (sq m),Lease Commencement Date,Yearly Gross Rent($),Floor Area Lower (sq m),Floor Area Upper (sq m)
BENCOOLEN HOUSE,BENCOOLEN STREET,7,Non-landed Properties,3,3280,110 to 120,May-19,39360,110 ,120
BENCOOLEN HOUSE,BENCOOLEN STREET,7,Non-landed Properties,3,3350,110 to 120,Aug-18,40200,110 ,120
CONCOURSE SKYLINE,BEACH ROAD,7,Non-landed Properties,3,10000,>300,Jan-19,120000,300,300
------------------------------------------------------------
Note that, following 3 fields are generated from other original fields by excel formula.

Yearly Gross Rent($)    = Monthly Gross Rent($) * 12
G2                      = Floor Area (sq m)
Floor Area Lower (sq m) =IF(LEFT(G2,1) <> ">", LEFT(G2, FIND("to", G2) -1), MID(G2,2,LEN(G2)))
Floor Area Upper (sq m) =IF(LEFT(G2,1) <> ">", RIGHT(G2, LEN(G2) - FIND("to", G2) - 2), MID(G2,2,LEN(G2)))
================================================================================

================================================================================
MySQL tables
------------------------------------------------------------
Create following tables and indexes:

URA_CONDOTRANS_HISTORY: mysql_tb_ura_condorent_history.sql
URA_CONDORENT_HISTORY:  mysql_tb_ura_condotrans_history.sql
------------------------------------------------------------
Import "Private Residential Property Transactions" CSV for URA_CONDOTRANS_HISTORY table.
Import "Rental Contracts of Private Residential Properties" CSV for URA_CONDORENT_HISTORY table.
================================================================================

================================================================================
MySQL query
------------------------------------------------------------
Run query SQL scripts in following file.

mysql_scripts.txt
================================================================================
