#!/bin/bash

INPUT_FILE_PREFIX=$1
OUTPUT_FILE_PREFIX=$2

HEADER_FILE=${INPUT_FILE_PREFIX}_H.csv
OUTPUT_FILE=${OUTPUT_FILE_PREFIX}_M.csv

echo "INPUT_FILE_PREFIX = "$INPUT_FILE_PREFIX
echo "HEADER_FILE = "$HEADER_FILE
echo "OUTPUT_FILE_PREFIX = "$OUTPUT_FILE_PREFIX
echo "OUTPUT_FILE = "$OUTPUT_FILE

cat $HEADER_FILE > $OUTPUT_FILE

for File in ${INPUT_FILE_PREFIX}_A*.csv ${INPUT_FILE_PREFIX}_B*.csv
do
	echo "File = "$File
    grep "^\"[0-9]" $File | grep -v "transacted\|transactions" >> $OUTPUT_FILE
done

sed -i -e "s/na\*/0/g" -e "s/\"-\"/\"\"/g" $OUTPUT_FILE
