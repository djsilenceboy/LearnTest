#!/bin/bash

DATE_RANGE_A="201706_202005"
DATE_RANGE_B="201706-202005"
BASE_FOLDER="./HtmlData_${DATE_RANGE_A}"
FROM_PERIOD="JUN 2017"
TO_PERIOD="MAY 2020"
TRANS_FILE_PREFIX="URA_CondoEcTrans_${DATE_RANGE_B}_"
RENT_FILE_PREFIX="URA_CondoEcRent_${DATE_RANGE_B}_"

# Generate download scripts.
echo "Date: "$(date +"%FT%T%z")
echo "Generate download scripts ..."

./URA_DownloadHtmlData_Generate.sh transaction ac "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}A" &
pids[1]=$!
./URA_DownloadHtmlData_Generate.sh transaction ec "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}B" &
pids[2]=$!
./URA_DownloadHtmlData_Generate.sh resiRental ac "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${RENT_FILE_PREFIX}A" &
pids[3]=$!
./URA_DownloadHtmlData_Generate.sh resiRental ec "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${RENT_FILE_PREFIX}B" &
pids[4]=$!

# Wait for all pids.
for pid in ${pids[*]}
do
	echo "Wait for PID $pid ..."
	wait $pid
done
echo "All PIDs completed."

echo "All download scripts generated."

# Run download scripts.
echo "Date: "$(date +"%FT%T%z")
echo "Run download scripts ..."

CURRENT_FOLDER=$(pwd)
cd ./${BASE_FOLDER}

./${TRANS_FILE_PREFIX}A.sh &
pids[1]=$!
./${TRANS_FILE_PREFIX}B.sh &
pids[2]=$!
./${RENT_FILE_PREFIX}A.sh &
pids[3]=$!
./${RENT_FILE_PREFIX}B.sh &
pids[4]=$!

# Wait for all pids.
for pid in ${pids[*]}
do
	echo "Wait for PID $pid..."
	wait $pid
done
echo "All PIDs completed."

cd ${CURRENT_FOLDER}

echo "All download scripts completed."
echo "Date: "$(date +"%FT%T%z")
