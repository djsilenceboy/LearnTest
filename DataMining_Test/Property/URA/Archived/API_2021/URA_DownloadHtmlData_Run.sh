#!/bin/bash

FROM_DATE=$1
TO_DATE=$2
FROM_PERIOD=$3
TO_PERIOD=$4

CURRENT_FOLDER=$(pwd)

DATE_RANGE_A="${FROM_DATE}_${TO_DATE}"
DATE_RANGE_B="${FROM_DATE}_${TO_DATE}"
BASE_FOLDER="${CURRENT_FOLDER}/HtmlData/${DATE_RANGE_A}"
TRANS_FILE_PREFIX="URA_CondoEcTrans_${DATE_RANGE_B}_"
RENT_FILE_PREFIX="URA_CondoEcRent_${DATE_RANGE_B}_"

echo "================================================================================"
# Generate download scripts.
echo "Date: "$(date +"%FT%T%z")
echo "DATE_RANGE_A = "$DATE_RANGE_A
echo "DATE_RANGE_B = "$DATE_RANGE_B
echo "BASE_FOLDER = "$BASE_FOLDER
echo "TRANS_FILE_PREFIX = "$TRANS_FILE_PREFIX
echo "RENT_FILE_PREFIX = "$RENT_FILE_PREFIX
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo
echo "Generate download scripts ..."

mkdir -p ${BASE_FOLDER}

${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh transaction ac "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}A" &
pids[1]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh transaction ec "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}B" &
pids[2]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh transaction lp "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}C" &
pids[3]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh transaction sl "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${TRANS_FILE_PREFIX}D" &
pids[4]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh resiRental ac "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${RENT_FILE_PREFIX}A" &
pids[5]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh resiRental ec "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${RENT_FILE_PREFIX}B" &
pids[6]=$!
${CURRENT_FOLDER}/URA_DownloadHtmlData_Generate.sh resiRental lp "${FROM_PERIOD}" "${TO_PERIOD}" ${BASE_FOLDER} "${RENT_FILE_PREFIX}C" &
pids[7]=$!

# Wait for all pids.
for pid in ${pids[*]}
do
	echo "Wait for PID $pid ..."
	wait $pid
done
echo "All PIDs completed."

echo "All download scripts generated."

echo "------------------------------------------------------------"
# Run download scripts.
echo "Date: "$(date +"%FT%T%z")
echo "Run download scripts ..."

cd ${BASE_FOLDER}
echo "CURRENT_FOLDER = "$(pwd)

./${TRANS_FILE_PREFIX}A.sh &
pids[1]=$!
./${TRANS_FILE_PREFIX}B.sh &
pids[2]=$!
./${TRANS_FILE_PREFIX}C.sh &
pids[3]=$!
./${TRANS_FILE_PREFIX}D.sh &
pids[4]=$!
./${RENT_FILE_PREFIX}A.sh &
pids[5]=$!
./${RENT_FILE_PREFIX}B.sh &
pids[6]=$!
./${RENT_FILE_PREFIX}C.sh &
pids[7]=$!

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
echo "================================================================================"
