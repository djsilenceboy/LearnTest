set FROM_DATE=%1%2
set TO_DATE=%3%4

echo ================================================================================
date /T
time /T
echo "FROM_DATE = "%FROM_DATE%
echo "TO_DATE = "%TO_DATE%
echo

echo "Start process data ..."

echo ------------------------------------------------------------
rem URA_ConvertHtmlToRawData.bat %FROM_DATE% %TO_DATE% & URA_PreprocessData.bat %FROM_DATE% %TO_DATE% & URA_ProcessDataInDb.bat %FROM_DATE% %TO_DATE%

URA_ConvertHtmlToRawData.bat %FROM_DATE% %TO_DATE% & URA_PreprocessData.bat %FROM_DATE% %TO_DATE%

rem URA_ProcessDataInDb.bat %FROM_DATE% %TO_DATE%

rem Above commands will exit this parent script.
