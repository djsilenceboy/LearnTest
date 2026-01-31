#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

${CurrentFolder}/URA_ProcessedCsvData_Filter.sh 201806 ${EndYear}${EndMonth}
