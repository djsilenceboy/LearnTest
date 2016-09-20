#!/bin/bash

set -v -x

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


echo "IFS = "$IFS
# "IFS" only visible in "while" line scope.
while IFS=. read A B C D
do
	echo "IFS = "$IFS
	echo $A $B $C $D
done <<< "192.168.10.25"
echo "IFS = "$IFS


# Pipeline is in sub-shell.
# "IFS" only visible in "while" line scope of sub-shell.
# "VarF" only visible in sub-shell scope.
# No need "local" identifier.
echo "192.168.10.25" | while IFS=. read A B C D
do
	echo "BASHPID = "$BASHPID;
	echo "IFS = "$IFS
	echo $A $B $C $D
	VarF=Clinton
done <<< "192.168.10.25"
echo
echo "IFS = "$IFS
echo "VarF = "$VarF
