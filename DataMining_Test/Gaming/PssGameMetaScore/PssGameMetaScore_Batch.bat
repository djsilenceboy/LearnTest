set DATE_TAG=20200713
set INPUT_FILE_1=PlayStationStore_GameList_20200713
set INPUT_FILE_2=Metacritic_GameList_20200712

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "INPUT_FILE_1 = "%INPUT_FILE_1%
echo "INPUT_FILE_2 = "%INPUT_FILE_2%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
PssGameMetaScore.bat %DATE_TAG% %INPUT_FILE_1% %INPUT_FILE_2%

rem Above commands will exit this parent script.
