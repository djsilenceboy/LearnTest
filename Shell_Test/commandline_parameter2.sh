#!/bin/bash

set -v

# This is similar to input from command line.
set Hello World Good Bye

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
