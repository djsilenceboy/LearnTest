set DATE_RANGE=%1
rem %2 can be empty/ignored or "Combined".
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\hdb
set DATA_FOLDER=.\ProcessedData%2\%DATE_RANGE%

echo ================================================================================
date /T
time /T
echo "DATE_RANGE = "%DATE_RANGE%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %DATA_FOLDER%
echo "Start process data in DB ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\ProcessDataInDb2.py -t "%DATA_FOLDER%\HDB_Trans_%DATE_RANGE%_MP.csv" -d "%DATA_FOLDER%\HDB_Trans.db" -o "%DATA_FOLDER%\HDB_Results_%DATE_RANGE%_"
echo ------------------------------------------------------------

date /T
time /T
echo "Process data in DB completed."
echo ================================================================================
