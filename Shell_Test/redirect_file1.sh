#!/bin/bash

set -v

LogFile=$TMP/temp.log

# FD1 message of printf will be saved in redirect file by default.
# FD2 error message of printf will not be saved in redirect file  by default.

echo "Step 1" > $LogFile
printf "%v\n" OK >> $LogFile
