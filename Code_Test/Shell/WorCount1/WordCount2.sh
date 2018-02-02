#!/bin/bash
# Hash order by key (word).

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

for Word in ${!WordCount[*]}
do
	echo $Word ${WordCount["$Word"]}
done
