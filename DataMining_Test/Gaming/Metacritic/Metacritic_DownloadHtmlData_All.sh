#!/bin/bash

TOTAL_PAGES=$1
DATE_TAG=$2

BASE_FOLDER="./HtmlData_${DATE_TAG}"
FILE_PREFIX="Metacritic_GameList_${DATE_TAG}_"

CURRENT_FOLDER=$(pwd)

echo "================================================================================"
echo "Date: "$(date +"%FT%T%z")
echo "TOTAL_PAGES = "$TOTAL_PAGES
echo "DATE_TAG = "$DATE_TAG
echo "BASE_FOLDER = "$BASE_FOLDER
echo "FILE_PREFIX = "$FILE_PREFIX
echo "CURRENT_FOLDER = "$CURRENT_FOLDER
echo
echo "Download ..."

mkdir -p ${BASE_FOLDER}

for ((N=0; N<$TOTAL_PAGES; N+=5))
do
	MAX=$(($N + 5))
	if [ $MAX -gt $TOTAL_PAGES ]; then
		MAX=$TOTAL_PAGES
	fi
	echo "(N, MAX) = ($N, $MAX)"

	pids=()
	for ((M=$N; M<$MAX; M++))
	do
		echo "(N, M) = ($N, $M)"
		${CURRENT_FOLDER}/Metacritic_DownloadHtmlData_One.sh $M ${BASE_FOLDER} "${FILE_PREFIX}${M}.html" &
		pids[$M]=$!
	done

	# Wait for all pids.
	for pid in ${pids[*]}
	do
		echo "Wait for PID $pid ..."
		wait $pid
	done
	echo "All PIDs completed."
done

cd ${CURRENT_FOLDER}

echo "All download completed."
echo "Date: "$(date +"%FT%T%z")
echo "================================================================================"
