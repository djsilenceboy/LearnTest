#!/bin/bash

set -v -x

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

# ${Var~PATTERN}
# Convert first matching character to reverted case.
# But not change variable.

Var="abcabcefgefg"

echo ${Var~}
echo $Var

echo ${Var~A}
echo $Var

echo ${Var~E}
echo $Var

Var="ABCABCEFGEFG"

echo ${Var~}
echo $Var

echo ${Var~A}
echo $Var

echo ${Var~E}
echo $Var

# ${Var~~PATTERN}
# Convert all matching characters to reverted case.
# But not change variable.

Var="AbCAbCEfGEfG"

echo ${Var~~}
echo $Var

echo ${Var~~A}
echo $Var

echo ${Var~~E}
echo $Var

echo ${Var~~[AE]}
echo $Var
