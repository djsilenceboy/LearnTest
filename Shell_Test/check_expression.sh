#!/bin/bash

set -v -x

((1 > 0))
echo $?

((1 == 0))
echo $?

((1 != 0))
echo $?

(("xyz"))
echo $?

X=$((1 + 1))
echo $X
