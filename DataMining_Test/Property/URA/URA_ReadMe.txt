================================================================================
URA property transaction / rental info

https://www.ura.gov.sg
https://www.ura.gov.sg/property-market-information/pmiResidentialTransactionSearch
================================================================================

================================================================================
URA_DownloadCsvData scripts.

Download data to such as:
URA\RawCsvData\202201_202212

and combined data to such as:
URA\RawCsvDataCombined\202201_202212\
  URA_CondoEcRent_201806_201812.csv
  URA_CondoEcTrans_201806_201812.csv
------------------------------------------------------------
URA_PreprocessCsvData scripts.

Preprocess data from such as:
URA\RawCsvDataCombined\202201_202212\
  URA_CondoEcRent_201806_201812.csv
  URA_CondoEcTrans_201806_201812.csv
to:
  URA_CondoEcRent_201806_201812_P.csv
  URA_CondoEcTrans_201806_201812_P.csv
------------------------------------------------------------
URA_CombinePreprocessedCsvData scripts.

Combined data from such as:
URA\RawCsvDataCombined\*\
  URA_CondoEcRent_*_P.csv
  URA_CondoEcTrans_*_P.csv
to such as:
URA\ProcessedCsvData\201806_202305
  URA_CondoEcRent_201806_202305_P.csv
  URA_CondoEcTrans_201806_202305_P.csv
------------------------------------------------------------
URA_ProcessCsvDataInDb scripts.

Process data from such as:
URA\ProcessedCsvData\201806_202305
  URA_CondoEcRent_201806_202305_P.csv
  URA_CondoEcTrans_201806_202305_P.csv
to DB:
  URA_CondoEcTransRent.db
then to final results, such as:
  URA_CondoEcResults_201806_202305_PriceRentRatio.csv
  URA_CondoEcResults_201806_202305_PriceSummary.csv
  URA_CondoEcResults_201806_202305_RentYearlyPrice.csv
  URA_CondoEcResults_201806_202305_TransPerMonth.csv
  URA_CondoEcResults_201806_202305_TransYearlyPrice.csv
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
