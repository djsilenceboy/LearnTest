#!/bin/bash

set -v

N=1
while [ $N \< 3 ]
do
	echo $N
	N=$(($N + 1))
done

N=1
while true
do
	echo $N
	N=$(($N + 1))
	if [ $N \> 3 ]; then
		break
	fi
done

# "while :" means "while true".
N=1
while :
do
	echo $N
	N=$(($N + 1))
	if [ $N \> 3 ]; then
		break
	fi
done

N=0
while [ $N \< 6 ]
do
	N=$(($N + 1))
	if (($N % 2)); then
		continue
	fi
	echo $N
done

# "while read" can also use input file "done < FILE".
while read VAR1 VAR2 VAR3
do
	echo $VAR1
	echo $VAR2
	echo $VAR3
done <<< "This is a Hello World"

N=1
until [ $N \> 3 ]
do
	echo $N
	N=$(($N + 1))
done

for Var in This is a Hello World
do
	echo $Var
done

for Var in 1 2 3
do
	echo $Var
done

for Var in {1..3}
do
	echo $Var
done

for ((N=1; N<4; N++))
do
	echo $N
done
