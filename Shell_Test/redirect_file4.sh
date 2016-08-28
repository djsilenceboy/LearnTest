#!/bin/bash

set -v

LogFile=$TMP/temp.log

# FD2 error message of printf will be saved in redirect file.
# FD1 and FD2 message will be saved in same file.

echo "Step 4" > $LogFile
printf "%v\n" OK 2>&1 >> $LogFile
