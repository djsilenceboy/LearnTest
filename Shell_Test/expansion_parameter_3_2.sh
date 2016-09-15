#!/bin/bash

set -v

Var="a b c d e"

# Get first word.
echo ${Var##* }

# Get last word.
echo ${Var%% *}

Var="   hello     "

# Only keep heading space.
VarH=":${Var%%[! ]*}:"
echo "$VarH"

# Only keep tailing space.
VarT=":${Var##*[! ]}:"
echo "$VarT"
