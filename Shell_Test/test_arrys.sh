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

# "+=" to add more elements.
# "[index]=" to define element with certain index.
VarArr+=(Tom [5]=Jerry)

echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}

# "[index]=" to define element with certain index.
VarArr[10]="Mary Cury"
VarArr[20]="Apple Pie"

echo ${VarArr[@]}
echo ${!VarArr[@]}
echo ${#VarArr[@]}

# Add one more element.
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


# Declare a associative array.
declare -A VarArr2

VarArr2["a"]=1
VarArr2["b"]=2
VarArr2["c"]=3

echo ${VarArr2[@]}
echo ${!VarArr2[@]}
echo ${#VarArr2[@]}

echo ${VarArr2["a"]}
echo ${VarArr2["b"]}


# Declare a associative array.
declare -A VarArr3

VarArr3[1, "a"]=1
VarArr3[2, "b"]=2
VarArr3[3, "c"]=3

echo ${!VarArr3[*]}
echo ${VarArr3[*]}

# Auto create array in first time, then increase size in following times.
for v in {a..e}
do
	VarArr3[${#VarArr3[@]}]=$v
done
echo ${VarArr3[@]}
echo ${!VarArr3[@]}


# Split by ":", then save as array.
Var="Alert:Chris:Jack:Lily"
IFS=: Names=($Var)
echo "${Names[@]}"



VarArr4=(
Hello
"Wor ld"
)

for v in ${VarArr4[*]}
do
	echo $v
done

for v in ${VarArr4[@]}
do
	echo $v
done

for v in "${VarArr4[*]}"
do
	echo $v
done

for v in "${VarArr4[@]}"
do
	echo $v
done
