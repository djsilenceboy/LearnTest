#!/bin/bash

set -v

LogFile=$TMP/temp.log
ErrorLogFile=$TMP/temp_error.log

# FD2 error message of printf will be saved in redirect file.
# FD1 and FD2 message will be saved in different files.

echo "Step 2" > $LogFile
printf "%v\n" OK >> $LogFile 2> $ErrorLogFile
