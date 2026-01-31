#!/bin/bash

CurrentFolder=$(pwd)
EndYear=$1
EndMonth=$2

${CurrentFolder}/URA_DownloadCsvData_Temp.sh ${EndYear} ${EndMonth}

${CurrentFolder}/URA_PreprocessCsvData_Temp.sh ${EndYear} ${EndMonth}

${CurrentFolder}/URA_CombinePreprocessedCsvData_Temp.sh ${EndYear} ${EndMonth}

${CurrentFolder}/URA_ProcessCsvDataInDb_Temp.sh ${EndYear} ${EndMonth}

${CurrentFolder}/URA_ProcessedCsvData_Filter_Temp.sh ${EndYear} ${EndMonth}
