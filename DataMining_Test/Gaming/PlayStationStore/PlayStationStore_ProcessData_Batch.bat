set DATE_TAG=20200713

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
PlayStationStore_RetrieveGameList.bat %DATE_TAG% & PlayStationStore_RetrieveSummaryData.bat %DATE_TAG%

rem Above commands will exit this parent script.
