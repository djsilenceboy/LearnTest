set DATE_TAG=%1
rem %2 can be empty/ignored or "Combined".
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\hdb
set DATA_FOLDER=.\ProcessedData%2\%DATE_TAG%

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %DATA_FOLDER%
echo "Start preprocessing data ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\PreprocessData2.py -d 0 -i "%DATA_FOLDER%\HDB_Trans_%DATE_TAG%_M.csv" -o "%DATA_FOLDER%\HDB_Trans_%DATE_TAG%_MP.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Preprocess data completed."
echo ================================================================================
