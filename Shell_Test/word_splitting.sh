#!/bin/bash

set -v -x

# Default delimiter = " \t\n".

Var="This is  
  Hello World."

printf "<%s>\n" $Var

Var="a : b : :: c"

# Delimiter = ":" plus any white space before and after.
IFS=' :'
printf "<%s>\n" $Var

# Delimiter = ":" plus any white space before and after.
IFS=': '
printf "<%s>\n" $Var

# Delimiter = ":".
IFS=:
printf "<%s>\n" $Var


# Split by ":", then save as array.
Var="Alert:Chris:Jack:Lily"
Names=($Var)
echo "${Names[@]}"
