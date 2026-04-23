set DATE_TAG=%1

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
HDB_PreprocessData.bat %DATE_TAG% "Combined" & HDB_ProcessDataInDb.bat %DATE_TAG% "Combined"

rem Above commands will exit this parent script.
