#!/bin/bash

MAIN_FOLDER=$1
LOG_FILE=$2

./CheckSum.sh $MAIN_FOLDER |& tee ${LOG_FILE}
