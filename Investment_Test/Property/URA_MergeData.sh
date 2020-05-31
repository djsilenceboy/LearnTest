#!/bin/bash

FILE_PREFIX=$1
NEW_FILE=${FILE_PREFIX}.csv
HEADER_FILE=${FILE_PREFIX}_H.csv

echo "FILE_PREFIX = "$FILE_PREFIX
echo "NEW_FILE = "$NEW_FILE
echo "HEADER_FILE = "$HEADER_FILE

cat $HEADER_FILE > $NEW_FILE

for File in ${FILE_PREFIX}_A*.csv ${FILE_PREFIX}_B*.csv
do
	echo "File = "$File
    grep "^\"[0-9]" $File | grep -v "transacted\|transactions" >> $NEW_FILE
done

sed -i -e "s/na\*/0/g" -e "s/\"-\"/\"\"/g" $NEW_FILE
