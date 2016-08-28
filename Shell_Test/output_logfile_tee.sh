#!/bin/bash

set -v

LogFile=$TMP/temp_tee.log

# This is similar to "Script 2>&1 | tee $LogFile" from command line.
exec > >(tee -ia $LogFile)
exec 2>&1

echo "This is Hello World".
