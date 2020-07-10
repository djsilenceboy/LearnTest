set FROM_DATE=201706
set TO_DATE=202005

echo ================================================================================
date /T
time /T
echo "FROM_DATE = "%FROM_DATE%
echo "TO_DATE = "%TO_DATE%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
URA_ConvertHtmlToRawData.bat %FROM_DATE% %TO_DATE% & URA_PreprocessData.bat %FROM_DATE% %TO_DATE% & URA_ProcessDataInDb.bat %FROM_DATE% %TO_DATE%

rem Above commands will exit this parent script.
