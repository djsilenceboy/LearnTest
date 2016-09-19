#!/bin/bash

set -v

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

# With prompt.
read -p "Please enter Y/N: " Var
echo $Var

# With timeout.
read -p "There is timeout: " -t 2 Var
echo $Var

# With initial text.
read -p "Change this: " -ei "Hello" Var
echo $Var
