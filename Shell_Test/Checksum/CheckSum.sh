#!/bin/bash

MAIN_FOLDER=$1

FOLDEER_LIST=$(ls -d ${MAIN_FOLDER}/*)

echo "FOLDEER_LIST = "$FOLDEER_LIST
echo

for folder in $FOLDEER_LIST
do
  echo "Check folder = "$folder
  # find "$folder" -type f -exec md5sum {} \; | md5sum
  find $folder -type f -exec bash -c 'md5sum "$0" | cut -d" " -f1' {} \; | md5sum
  echo
done

echo "Completed."
