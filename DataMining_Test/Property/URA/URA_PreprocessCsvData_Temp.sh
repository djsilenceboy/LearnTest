#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

# ${CurrentFolder}/URA_PreprocessCsvData.sh 201806 201812
# ${CurrentFolder}/URA_PreprocessCsvData.sh 201901 201912
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202001 202012
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202101 202112
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202201 202212
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202301 202312
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202401 202412
# ${CurrentFolder}/URA_PreprocessCsvData.sh 202501 202512
${CurrentFolder}/URA_PreprocessCsvData.sh ${EndYear}01 ${EndYear}${EndMonth}
