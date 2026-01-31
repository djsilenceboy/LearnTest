#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

${CurrentFolder}/URA_ProcessCsvDataInDb.sh 201806 ${EndYear}${EndMonth}
