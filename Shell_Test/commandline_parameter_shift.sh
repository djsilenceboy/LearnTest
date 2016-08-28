#!/bin/bash

set -v

# This is similar to input from command line.
set 1 2 3 4 5 6 7 8 9 a b c

# $@: All command line parameters.
echo $@

# $#: The number of command line parameters.
echo $#

# $10 = $1 + '0'
# ${10} means the 10th parameter.
echo $10 ${10}

# Left shift all parameters.
# So, $1 will take values from $2, $3, ...
while [ $# -gt 0 ];
do
	echo $1
	shift
done
