#!/bin/bash

set -v -x

Var="Hello World"
Var2=Var

# Indirect reference.
eval "echo \$$Var2"


# Var3=$Var
eval "Var3=\$Var"
echo $Var3

# Become wrong:
# Var4='Hello '
# echo World
Var='Hello " echo World"'
eval "Var4=\"$Var\""
echo $Var4
