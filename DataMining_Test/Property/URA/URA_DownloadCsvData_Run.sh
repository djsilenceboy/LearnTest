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
Cookie=JSESSIONID=PIAW3NMEjFM3OVUqJTld5P-74GWuzmUDz-DCcZKuEKBt2PHAOsPK!-526713347!1486920979
Csrf=5eed1fd1-8d09-4a2a-9ad3-e4225c704296

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
echo "Cookie = "$Cookie
echo "Csrf = "$Csrf
echo

mkdir -p ${RawFileFolder}

${CurrentFolder}/URA_DownloadCsvData_Generate_Transaction.sh $FromYear $FromMonth $ToYear $ToMonth "${RawFileFolder}" "${TransFileMainName}" "${Cookie}" "${Csrf}"

${CurrentFolder}/URA_DownloadCsvData_Generate_Rental.sh $FromYear $FromMonth $ToYear $ToMonth "${RawFileFolder}" "${RentFileMainName}" "${Cookie}" "${Csrf}"

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
