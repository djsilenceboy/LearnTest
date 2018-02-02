#!/bin/bash
# Original order by key (word).

declare -A WordCount
declare -a WordOrder
while read Line
do
	Words=($Line)
	for Word in ${Words[*]}
	do
		if [ ${WordCount["$Word"]} ]; then
			((WordCount["$Word"]++))
		else
			WordCount["$Word"]=1
			WordOrder+=("$Word")
		fi
	done
done

for Word in ${WordOrder[*]}
do
	echo $Word ${WordCount["$Word"]}
done
