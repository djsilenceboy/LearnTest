#!/bin/bash

set -v -x

# Use "$$" to get process ID for current shell.
# Use "$BASHPID" to get process ID for sub shell. If not a sub shell, then for current shell.

echo "PID = "$$
echo "BASHPID = "$BASHPID

echo `date`

echo `ls -l | grep read`

echo `echo "BASHPID = "$BASHPID`

echo $(date;  echo; echo "BASHPID = "$BASHPID)

echo $(ls -l | grep read)

echo $(echo "BASHPID = "$BASHPID)
