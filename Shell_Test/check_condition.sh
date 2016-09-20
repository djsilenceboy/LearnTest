#!/bin/bash

set -v -x

test 1 -eq 1
echo $?

[ 1 -eq 1 ]
echo $?

[ 1 = 1 ]
echo $?

[ 1 != 1 ]
echo $?

test "a" = "b"
echo $?

[ "a" = "b" ]
echo $?

[ "a" == "b" ]
echo $?

[[ "a" = "b" ]]
echo $?

[ -z "" ]
echo $?

[ -z "xyz" ]
echo $?

[ -n "" ]
echo $?

[ -n "xyz" ]
echo $?

test "a" \< "b"
echo $?

[ "a" \< "b" ]
echo $?

test 1 \< 0
echo $?

[ 1 \< 0 ]
echo $?

[ 1 -lt 0 ]
echo $?

test 1 = 1 -a 0 = 0
echo $?

[ 1 = 1 -a 0 = 0 ]
echo $?

[ 1 = 1 -a 1 = 0 ]
echo $?

[ 1 = 0 -o 0 = 0 ]
echo $?

[ 1 = 1 -o 1 = 0 ]
echo $?

[[ 1 = 1 && 0 = 0 ]]
echo $?

[[ 1 = 1 && 1 = 0 ]]
echo $?

[[ 1 = 0 || 0 = 0 ]]
echo $?

[[ 1 = 1 || 1 = 0 ]]
echo $?

[[ "Y" = [Yy] ]]
echo $?

[[ "Y" =~ [Yy] ]]
echo $?

[[ "Yn" = [Yy]n ]]
echo $?

[[ "Yn" =~ [Yy]n ]]
echo $?
