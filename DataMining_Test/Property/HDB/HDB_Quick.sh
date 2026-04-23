#!/bin/bash

CurrentFolder=$(pwd)
DateRange=$1

${CurrentFolder}/HDB_CombineCsvData.sh ${DateRange}

${CurrentFolder}/HDB_PreprocessData.sh ${DateRange}

${CurrentFolder}/HDB_ProcessDataInDb.sh ${DateRange}
