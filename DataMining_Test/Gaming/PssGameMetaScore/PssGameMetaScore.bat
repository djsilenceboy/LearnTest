set DATE_TAG=%1
set INPUT_FILE_1=%2
set INPUT_FILE_2=%3
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\gaming
set DATA_FOLDER=.\ProcessedData

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "INPUT_FILE_1 = "%INPUT_FILE_1%
echo "INPUT_FILE_2 = "%INPUT_FILE_2%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %DATA_FOLDER%
echo "Start process data in DB ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\PssGameMetaScore.py -p "%DATA_FOLDER%\%INPUT_FILE_1%.csv" -m "%DATA_FOLDER%\%INPUT_FILE_2%.csv" -d "%DATA_FOLDER%\PssGameMetaScore_%DATE_TAG%.db" -o "%DATA_FOLDER%\PssGameMetaScore_%DATE_TAG%_"
echo ------------------------------------------------------------

date /T
time /T
echo "Process data in DB completed."
echo ================================================================================
