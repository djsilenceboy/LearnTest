set DATE_TAG=20200712

echo ================================================================================
date /T
time /T
echo "DATE_TAG = "%DATE_TAG%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
Metacritic_ConvertHtmlToRawData.bat %DATE_TAG%

rem Above commands will exit this parent script.
