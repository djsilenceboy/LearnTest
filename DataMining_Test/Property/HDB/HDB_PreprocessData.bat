set DATE_TAG=%1
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\hdb
set DATA_FOLDER=.\ProcessedData_%DATE_TAG%

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %OUTPUT_FOLDER%
echo "Start preprocessing data ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\PreprocessData.py -d 0 -i "%DATA_FOLDER%\HDB_Trans_%DATE_TAG%_M.csv" -o "%DATA_FOLDER%\HDB_Trans_%DATE_TAG%_MP.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Preprocess data completed."
echo ================================================================================
