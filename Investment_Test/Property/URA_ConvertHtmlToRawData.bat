set DATE_RANGE=%1_%2
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\ura
set INPUT_DATE_RANGE=.\HtmlData_%DATE_RANGE%
set OUTPUT_FOLDER=.\ProcessedData_%DATE_RANGE%

echo ================================================================================
date /T
time /T
echo "DATE_RANGE = "%DATE_RANGE%
echo "APP_FOLDER = "%APP_FOLDER%
echo "INPUT_DATE_RANGE = "%INPUT_DATE_RANGE%
echo "OUTPUT_FOLDER = "%OUTPUT_FOLDER%
echo

md %OUTPUT_FOLDER%
echo "Start converting ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\ConvertHtmlToRawData.py -f "%INPUT_DATE_RANGE%" -i "URA_CondoEcTrans_%DATE_RANGE%_" -o "%OUTPUT_FOLDER%\URA_CondoEcTrans_%DATE_RANGE%_M.csv"
echo ------------------------------------------------------------
python %APP_FOLDER%\ConvertHtmlToRawData.py -f "%INPUT_DATE_RANGE%" -i "URA_CondoEcRent_%DATE_RANGE%_" -o "%OUTPUT_FOLDER%\URA_CondoEcRent_%DATE_RANGE%_M.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Convert completed."
echo ================================================================================
