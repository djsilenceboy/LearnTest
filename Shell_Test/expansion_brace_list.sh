#!/bin/bash

set -v -x

# No space between elements of the list in {}!

echo {one,two,three}

echo {1..3}

echo {1..7..2}

echo {a..c}

echo {a..f..2}

echo {{1..3},{a..c}}

echo {1..3},{a..c}

echo {1..3}{a..c}

echo {{1..3}{a..c}}

printf "%s\n" {one,two,three}

printf "%s\n" {1..3}

printf "%s\n" {a..c}

printf "%s\n" {{1..3},{a..c}}

printf "%s\n" {1..3},{a..c}

printf "%s\n" {1..3}{a..c}

printf "%s\n" {{1..3}{a..c}}

echo To{D,d}{A,a}y
