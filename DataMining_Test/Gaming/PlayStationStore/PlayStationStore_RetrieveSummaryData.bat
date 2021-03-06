set DATE_TAG=%1
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\playstationstore
set DATA_FOLDER=.\RawData

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %DATA_FOLDER%
echo "Start retrieving data ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\RetrieveSummaryData.py -d 0 -o "%DATA_FOLDER%\PlayStationStore_Summary_%DATE_TAG%.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Retrieving data completed."
echo ================================================================================
