#!/bin/bash

set -v -x

read a b c d <<< "This is a Hello World"

echo $a
echo $b
echo $c
echo $d


# "IFS" only visible in "read" line scope.
IFS=. read a b c d <<< "192.168.10.25"

echo $a
echo $b
echo $c
echo $d


# Default variable is "$REPLY"
read <<< "This is a Hello World"
echo $REPLY

# With prompt, only read one character.
read -p "Please enter Y/N: " -n1 Var
echo $Var

# With prompt, only read one character, no echo input.
read -p "Press any key..." -sn1 Var
echo $Var

# With timeout.
read -p "There is timeout." -t2 Var
echo $Var

# With changeable initial text.
# "-i" must and only be used together with "-e".
read -p "Change initial text: " -ei "Hello" Var
echo $Var

Spinner="\|/-"
until read -sn1 -t0.1 -p "Press any key..." Var
do
	printf "%.1s\r" $Spinner
	temp=${Spinner#?}
	Spinner=$temp${Spinner%"$temp"}
done
