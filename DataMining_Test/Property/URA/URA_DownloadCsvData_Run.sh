#!/bin/bash

FromYear=$1
FromMonth=$2
ToYear=$3
ToMonth=$4

CurrentFolder=$(pwd)
DateRange="${FromYear}${FromMonth}_${ToYear}${ToMonth}"
RawFileFolder="${CurrentFolder}/RawCsvData/${DateRange}"
RawCombinedFileFolder="${CurrentFolder}/RawCsvDataCombined/${DateRange}"
TransFileMainName="URA_CondoEcTrans_${DateRange}"
RentFileMainName="URA_CondoEcRent_${DateRange}"

echo "================================================================================"
echo "Generate download scripts ..."
echo "Date: "$(date +"%FT%T%z")

echo "CurrentFolder = "$CurrentFolder
echo "FromYear = "$FromYear
echo "FromMonth = "$FromMonth
echo "ToYear = "$ToYear
echo "ToMonth = "$ToMonth
echo "DateRange = "$DateRange
echo "RawFileFolder = "$RawFileFolder
echo "RawCombinedFileFolder = "$RawCombinedFileFolder
echo "TransFileMainName = "$TransFileMainName
echo "RentFileMainName = "$RentFileMainName
echo

mkdir -p ${RawFileFolder}

${CurrentFolder}/URA_DownloadCsvData_Generate_Transaction.sh $FromYear $FromMonth $ToYear $ToMonth "${RawFileFolder}" "${TransFileMainName}"

${CurrentFolder}/URA_DownloadCsvData_Generate_Rental.sh $FromYear $FromMonth $ToYear $ToMonth "${RawFileFolder}" "${RentFileMainName}"

echo "Generated download scripts."

echo "================================================================================"
echo "Run download scripts ..."
echo "Date: "$(date +"%FT%T%z")

cd ${RawFileFolder}
echo "CurrentFolder = "$(pwd)

N=1
for shFile in *.sh
do
    echo "shFile = "$shFile
    ./$shFile &
    pids[$N]=$!
    echo "pid = "${pids[$N]}

    N=$(($N + 1))
done

# Wait for all pids.
for pid in ${pids[*]}
do
	echo "Wait for PID $pid..."
	wait $pid
done
echo "All PIDs completed."

cd ${CurrentFolder}

echo "Completed download scripts."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
echo "Combine downloaded CSV files ..."
echo "Date: "$(date +"%FT%T%z")

mkdir -p ${RawCombinedFileFolder}

${CurrentFolder}/URA_DownloadCsvData_Combine.sh "${RawFileFolder}" "${RawCombinedFileFolder}" "${TransFileMainName}" "${RentFileMainName}"

echo "Combined downloaded CSV files."
echo "Date: "$(date +"%FT%T%z")

echo "================================================================================"
