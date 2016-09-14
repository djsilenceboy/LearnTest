#!/bin/bash
# Test parameter: Hello World Good Bye

set -v

# $*: All command line parameters.
echo $*

# $@: All command line parameters.
echo $@

# $#: The number of command line parameters.
echo $#

# $0: The path of this script.
echo $0

# $1: The first command line parameters.
echo $1

# $$: PID of this process.
echo $$

# $!: PID of last process.
echo $!

# $-: current option flag.
echo $-

# $-: The exit code of previous command.
echo $?

# $_: The previous command.
echo $_

# ${Var##PATTERN}
# Remove longest match from beginning.
# But not change variable.
# ${0##*/}: The base name (without path) of this script.
echo ${0##*/}

# The base name (without path) of this script.
echo `basename $0`
