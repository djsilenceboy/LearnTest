#!/bin/bash

set -v -x

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


Var2=Var

# ${!Var2}
# Indirect reference.
echo ${!Var2}

# Without "!", it is normal variable.
echo ${Var2}

# Similar indirect reference by eval.
eval "echo \$$Var2"

