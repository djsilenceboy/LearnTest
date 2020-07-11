set DATE_RANGE=%1_%2
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\ura
set DATA_FOLDER=.\ProcessedData_%DATE_RANGE%

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
python %APP_FOLDER%\ProcessDataInDb.py -t "%DATA_FOLDER%\URA_CondoEcTrans_%DATE_RANGE%_MP.csv" -r "%DATA_FOLDER%\URA_CondoEcRent_%DATE_RANGE%_MP.csv" -d "%DATA_FOLDER%\URA_CondoEcTransRent.db" -o "%DATA_FOLDER%\URA_CondoEcResults_%DATE_RANGE%_"
echo ------------------------------------------------------------

date /T
time /T
echo "Process data in DB completed."
echo ================================================================================
