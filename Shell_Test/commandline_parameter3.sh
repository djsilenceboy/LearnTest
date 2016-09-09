#!/bin/bash

set -v

# This is similar to input from command line.
set 1 '2 3' 4\ 5 "6 7"

# $@: All command line parameters.
echo $@

# $#: The number of command line parameters.
echo $#

# Notice the difference of "$@" and "$*"!

# Use "$*", do not forget double quotes.
printf "%s\n" "$*"

# Use "$@", do not forget double quotes.
printf "%s\n" "$@"
