#!/bin/bash
# Descending order by value (frequency), Not completed.

declare -A WordCount
while read Line
do
	Words=($Line)
	for Word in ${Words[*]}
	do
		if [ ${WordCount["$Word"]} ]; then
			((WordCount["$Word"]++))
		else
			WordCount["$Word"]=1
		fi
	done
done

declare -A WordCount2
for Word in ${!WordCount[*]}
do
	Count=${WordCount["$Word"]}
	WordCount2[$Count,$Word]=1
done

for Key in ${!WordCount2[*]}
do
	IPS=,
	Item=($Key)
	echo ${Item[@]}
	IPS=
done
