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


# Split by ":", and assign to each variable by indirect reference.
Var="Alert:Chris:Jack:Lily"
for v in ChildA ChildB ChildC ChildD
do
	eval "$v=\${Var%%:*}"
	Var=${Var#*:}
done
echo $ChildA $ChildB $ChildC $ChildD
