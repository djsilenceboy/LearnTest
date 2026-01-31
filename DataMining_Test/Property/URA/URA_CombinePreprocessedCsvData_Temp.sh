#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

${CurrentFolder}/URA_CombinePreprocessedCsvData.sh 201806 ${EndYear}${EndMonth}
