set DATE_TAG=20200627_12m

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
HDB_ConvertHtmlToRawData.bat %DATE_TAG% & HDB_PreprocessData.bat %DATE_TAG% & HDB_ProcessDataInDb.bat %DATE_TAG%

rem Above commands will exit this parent script.
