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
