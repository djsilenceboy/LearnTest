#!/bin/bash

set -v

# Use "$$" to get process ID for current shell.
# Use "$BASHPID" to get process ID for sub shell. If not a sub shell, then for current shell.

echo "PID = "$$
echo "BASHPID = "$BASHPID


# Function definition can be in following formats:
# function action()
# function action
# action()

# Function body can be inside:
# {}, which in current shell scope.
# (), which in sub-shell scope.


# Function with {} body.
actionA()
{
echo "BASHPID = "$BASHPID
echo "VarA = "$VarA

# Visible in current shell scope.
VarB=Jerry
echo "VarB = "$VarB

# With "local" identifier, only visible in sub-shell scope.
local VarC=Jack
echo "VarC = "$VarC
}

# Function with () body.
actionB()
(
echo "BASHPID = "$BASHPID
echo "VarA = "$VarA

# Only visible in sub-shell scope.
# No need "local" identifier.
VarD=John
echo "VarD = "$VarD
)

# Visible in current shell scope.
VarA=Tom
echo "VarA = "$VarA

actionA

echo "VarB = "$VarB

echo "VarC = "$VarC

actionB

echo "VarD = "$VarD

# "VarE" only visible in sub-shell scope.
# No need "local" identifier.
(echo "BASHPID = "$BASHPID;
 echo "VarA = "$VarA;
 VarE=Thomas)

echo "VarE = "$VarE
