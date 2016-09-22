#!/bin/bash

set -v -x

PropertyFile=sample_properties.txt
IFS=$'\n'

# Import file into array.
# One line one element.
settings=($(<"$PropertyFile"))
echo "${settings[@]}"

Entries=("${settings[@]%%#*}")
echo "${Entries[@]}"
