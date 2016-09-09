#!/bin/bash

set -v

# Default delimiter = " \t\n".

VAR="This is  
  Hello World."

printf "<%s>\n" $VAR

VAR="a : b : :: c"

# Delimiter = ":" plus any white space before and after.
IFS=' :'

printf "<%s>\n" $VAR

# Delimiter = ":" plus any white space before and after.
IFS=': '

printf "<%s>\n" $VAR

# Delimiter = ":".
IFS=:

printf "<%s>\n" $VAR
