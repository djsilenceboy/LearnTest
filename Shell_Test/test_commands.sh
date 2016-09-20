#!/bin/bash

set -v -x

ls | cut -c 1-4,8-9

ls | wc -l

# sed 'From,To s/Old/New/g'
ls | sed '/^co.*/,/^ra.*/ s/\.sh/\.txt/g'

# Find min length file name.
ls | awk -F=\  'BEGIN {min = 999}
length($0) < min {
min = length($0)
filename = $0}
END {print min, filename}'
