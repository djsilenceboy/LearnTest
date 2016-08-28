#!/bin/bash

set -v

LogFile=$TMP/temp.log
ErrorLogFile=$TMP/temp_error.log

echo > $LogFile
echo > $ErrorLogFile

echo "Step 1" >> $LogFile
printf "%v\n" OK >> $LogFile

echo "Step 2" >> $LogFile
printf "%v\n" OK >> $LogFile 2>> $ErrorLogFile

echo "Step 3" >> $LogFile
printf "%v\n" OK 2>&1 >> $LogFile
