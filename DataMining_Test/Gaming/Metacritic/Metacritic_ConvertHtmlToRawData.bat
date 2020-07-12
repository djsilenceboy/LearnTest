set DATE_TAG=%1
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\metacritic
set INPUT_DATE_TAG=.\HtmlData_%DATE_TAG%
set OUTPUT_FOLDER=.\ProcessedData

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
python %APP_FOLDER%\ConvertHtmlToRawData.py -f "%INPUT_DATE_TAG%" -i "Metacritic_GameList_%DATE_TAG%_" -o "%OUTPUT_FOLDER%\Metacritic_GameList_%DATE_TAG%.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Convert completed."
echo ================================================================================
