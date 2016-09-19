#!/bin/bash

set -v

time echo {1..30000} > /dev/null

LogFile=$TMP/temp.log
# To get output of time, {} codes and redirect FD2.
{ time echo {1..30000} > /dev/null; } 2> $LogFile

# Define time format.
TIMEFORMAT='%R seconds %P%% CPU usage'
time echo {1..30000} > /dev/null
