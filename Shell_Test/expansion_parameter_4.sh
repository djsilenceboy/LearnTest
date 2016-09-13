#!/bin/bash

set -v

Var=a1b1c2d2e

# ${#Var}
# Length of content.

echo ${#Var}


# ${Var:Offset}
# ${Var:Offset:Length}
# Sub-string with positive offset.

echo ${Var:0:2}

echo ${Var:2:4}

echo ${Var:6}

# ${Var: -Offset}
# ${Var: -Offset:Length}
# Sub-string with negative offset.
# There is a space between ":" and "-".

echo ${Var: -2:2}

echo ${Var: -4:2}

echo ${Var: -6}


# ${!Var2}
# Indirect reference.

Var2=Var
echo ${!Var2}

# Without "!", it is normal variable.
echo ${Var2}


# ${Var^PATTERN}
# Convert first matching character to upper-case.
# But not change variable.

Var="abcabcefgefg"

echo ${Var^}
echo $Var

echo ${Var^a}
echo $Var

echo ${Var^e}
echo $Var

# ${Var^^PATTERN}
# Convert all matching characters to upper-case.
# But not change variable.

echo ${Var^^}
echo $Var

echo ${Var^^a}
echo $Var

echo ${Var^^e}
echo $Var

echo ${Var^^[ae]}
echo $Var


# ${Var,PATTERN}
# Convert first matching character to lower-case.
# But not change variable.

Var="ABCABCEFGEFG"

echo ${Var,}
echo $Var

echo ${Var,A}
echo $Var

echo ${Var,E}
echo $Var

# ${Var,,PATTERN}
# Convert all matching characters to lower-case.
# But not change variable.

echo ${Var,,}
echo $Var

echo ${Var,,A}
echo $Var

echo ${Var,,E}
echo $Var

echo ${Var,,[AE]}
echo $Var

# ${Var~PATTERN} equals to ${Var,PATTERN}
# Convert first matching character to lower-case.
# But not change variable.

Var="ABCABCEFGEFG"

echo ${Var~}
echo $Var

echo ${Var~A}
echo $Var

echo ${Var~E}
echo $Var

# ${Var~~PATTERN} equals to ${Var,,PATTERN}
# Convert all matching characters to lower-case.
# But not change variable.

echo ${Var~~}
echo $Var

echo ${Var~~A}
echo $Var

echo ${Var~~E}
echo $Var

echo ${Var~~[AE]}
echo $Var
