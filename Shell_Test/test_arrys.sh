#!/bin/bash

set -v -x

# Without double quotes, [*] equals to [@].
# With double quotes, "[*]" does not equal to "[@]".
# "[*]" expands all elements into one pair of double quotes.
# "[@]" expands each of elements into a pair of double quotes.


VarArr=(Hello World)

echo ${VarArr[*]}
echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}

VarArr+=(Tom [5]=Jerry)

echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}

VarArr[10]="Mary Cury"
VarArr[20]="Apple Pie"

echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}

VarArr[${#VarArr[@]}]="Cat"

echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}


for n in ${!VarArr[@]}
do
	echo $n ${VarArr[n]}
done


for v in ${VarArr[*]}
do
	echo $v
done

for v in ${VarArr[@]}
do
	echo $v
done

for v in "${VarArr[*]}"
do
	echo $v
done

for v in "${VarArr[@]}"
do
	echo $v
done


declare -A VarArr2

VarArr2["a"]=1
VarArr2["b"]=2
VarArr2["c"]=3

echo ${VarArr2[@]}
echo ${!VarArr2[@]}
echo ${#VarArr2[@]}

echo ${VarArr2["a"]}
echo ${VarArr2["b"]}


# Auto create array in first time, then increase size in following times.
for v in {a..e}
do
	VarArr3[${#VarArr3[@]}]=$v
done
echo ${VarArr3[@]}
echo ${!VarArr3[@]}
