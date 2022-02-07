#!/bin/bash

CURRENT_FOLDER=$(pwd)

# Declare a associative array.
declare -A Month

Month["01"]="JAN"
Month["02"]="FEB"
Month["03"]="MAR"
Month["04"]="APR"
Month["05"]="MAY"
Month["06"]="JUN"
Month["07"]="JUL"
Month["08"]="AUG"
Month["09"]="SEP"
Month["10"]="OCT"
Month["11"]="NOV"
Month["12"]="DEC"

for Year in {2017..2021}
do
	for n in ${!Month[@]}
	do
		${CURRENT_FOLDER}/URA_DownloadHtmlData_Main.sh $Year $n ${Month[$n]} $Year $n ${Month[$n]}
	done
done
