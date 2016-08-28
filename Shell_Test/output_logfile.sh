#!/bin/bash

set -v

LogFile=$TMP/temp.log

# This is similar to "2>&1 > $LogFile" from command line.
exec &> $LogFile
exec 2>&1

echo "This is Hello World".
