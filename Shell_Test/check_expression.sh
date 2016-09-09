#!/bin/bash

set -v

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
