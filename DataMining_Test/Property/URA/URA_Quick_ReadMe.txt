================================================================================
All in one sh.
------------------------------------------------------------
From following page,
https://eservice.ura.gov.sg/property-market-information/pmiResidentialTransactionSearch
https://eservice.ura.gov.sg/property-market-information/pmiResidentialRentalSearch
find latest "JSESSIONID" and "Csrf".
------------------------------------------------------------
Update "URA_DownloadCsvData_Run.sh":

Cookie=JSESSIONID=xxx
Csrf=yyy
------------------------------------------------------------
In Git Bash, run:
./URA_Quick.sh YYYY MM

Such as:
./URA_Quick.sh 2026 09
================================================================================

================================================================================
Update "URA_DownloadCsvData_Temp.sh".

In Git Bash, run:
./URA_DownloadCsvData_Temp.sh 2026 02
------------------------------------------------------------
Update "URA_PreprocessCsvData_Temp.bat".

In Windows Prompt, run:
URA_PreprocessCsvData_Temp.bat

In Git Bash, run:
./URA_PreprocessCsvData_Temp.sh 2026 02
------------------------------------------------------------
Update "URA_CombinePreprocessedCsvData_Temp.sh".

In Git Bash, run:
./URA_CombinePreprocessedCsvData_Temp.sh 2026 02
------------------------------------------------------------
Update "URA_ProcessCsvDataInDb_Temp.bat".

In Windows Prompt, run:
URA_ProcessCsvDataInDb_Temp.bat

In Git Bash, run:
./URA_ProcessCsvDataInDb_Temp.sh 2026 02
------------------------------------------------------------
Update "URA_ProcessedCsvData_Filter_Temp.sh".

In Git Bash, run:
./URA_ProcessedCsvData_Filter_Temp.sh 2026 02
================================================================================
