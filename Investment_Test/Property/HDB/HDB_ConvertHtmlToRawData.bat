set DATE_TAG=%1
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\hdb
set INPUT_DATE_TAG=.\HtmlData_%DATE_TAG%
set OUTPUT_FOLDER=.\ProcessedData_%DATE_TAG%

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo "APP_FOLDER = "%APP_FOLDER%
echo "INPUT_DATE_TAG = "%INPUT_DATE_TAG%
echo "OUTPUT_FOLDER = "%OUTPUT_FOLDER%
echo

md %OUTPUT_FOLDER%
echo "Start converting ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\ConvertHtmlToRawData.py -f "%INPUT_DATE_TAG%" -i "HDB_Trans_%DATE_TAG%_" -o "%OUTPUT_FOLDER%\HDB_Trans_%DATE_TAG%_M.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Convert completed."
echo ================================================================================
