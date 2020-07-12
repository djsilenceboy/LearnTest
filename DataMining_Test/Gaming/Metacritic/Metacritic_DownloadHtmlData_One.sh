#!/bin/bash

# PAGE: [0, )
PAGE=$1
OUTPUT_FOLDER=$2
OUTPUT_FILE=$3

echo "------------------------------------------------------------"
echo "PAGE = "$PAGE
echo "OUTPUT_FOLDER = "$OUTPUT_FOLDER
echo "OUTPUT_FILE = "$OUTPUT_FILE

make_query_params()
{
	L_PAGE=$1
	L_QUERY_PARAMS="?view=condensed&page=${L_PAGE}"
	echo $L_QUERY_PARAMS
}

QUERY_PARAMS=$(make_query_params ${PAGE})
echo "QUERY_PARAMS = "$QUERY_PARAMS

ACTION_URL="https://www.metacritic.com/browse/games/score/metascore/all/all"${QUERY_PARAMS}
echo "ACTION_URL = "$ACTION_URL
echo "Start downloading ${OUTPUT_FILE} ..."

SLEEP_TIME=10
while true
do
	curl -sS "${ACTION_URL}" --compressed -o "${OUTPUT_FOLDER}/${OUTPUT_FILE}"
	COUNT=$(grep "clamp-list condensed" "${OUTPUT_FOLDER}/${OUTPUT_FILE}" | wc -l)
	if [ $COUNT \> 3 ]; then
		break
	fi
	sleep $SLEEP_TIME
	SLEEP_TIME=$(($SLEEP_TIME + 10))
	echo "Re-downloading ${OUTPUT_FILE} ..."
done

echo "Download ${OUTPUT_FILE} completed."
echo "------------------------------------------------------------"
