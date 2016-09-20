#!/bin/bash

set -v -x

Var=a1b1c2d2e

# ${Var%PATTERN}
# Remove shortest match from end.
# But not change variable.

echo ${Var%2*}
echo $Var

# ${Var%%PATTERN}
# Remove longest match from end.
# But not change variable.

echo ${Var%%2*}
echo $Var


# ${Var#PATTERN}
# Remove shortest match from beginning.
# But not change variable.

echo ${Var#*1}
echo $Var

# ${Var##PATTERN}
# Remove longest match from beginning.
# But not change variable.

echo ${Var##*1}
echo $Var

# ${Var//PATTERN/REPLACE}
# Replace all pattern match.
# But not change variable.

echo ${Var//1/X}
echo $Var

echo ${Var//?/X}
echo $Var

# ${Var/PATTERN/REPLACE}
# Replace first pattern match.
# But not change variable.

echo ${Var/1/X}
echo $Var

echo ${Var/?/X}
echo $Var
