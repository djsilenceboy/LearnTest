set DATE_RANGE=%1_%2
set APP_FOLDER=F:\WorkDJS\RepositoryGit\LearnTest\Python_Test\PyDataMiningSample\com\djs\learn\ura
set DATA_FOLDER=.\RawCsvDataCombined\%DATE_RANGE%

echo ================================================================================
date /T
time /T
echo "DATE_RANGE = "%DATE_RANGE%
echo "APP_FOLDER = "%APP_FOLDER%
echo "DATA_FOLDER = "%DATA_FOLDER%
echo

md %DATA_FOLDER%
echo "Start preprocessing CSV data ..."

echo ------------------------------------------------------------
python %APP_FOLDER%\PreprocessCsvData.py -d 0 -i "%DATA_FOLDER%\URA_CondoEcTrans_%DATE_RANGE%.csv" -o "%DATA_FOLDER%\URA_CondoEcTrans_%DATE_RANGE%_P.csv"
echo ------------------------------------------------------------
python %APP_FOLDER%\PreprocessCsvData.py -d 1 -i "%DATA_FOLDER%\URA_CondoEcRent_%DATE_RANGE%.csv" -o "%DATA_FOLDER%\URA_CondoEcRent_%DATE_RANGE%_P.csv"
echo ------------------------------------------------------------

date /T
time /T
echo "Preprocess CSV data completed."
echo ================================================================================
