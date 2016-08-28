#!/bin/bash

set -v

printf "%04d" 12

# printf can assign value to a variable by "-v", instead of output.
printf -v NUM "%04d" 12

echo $NUM

# printf can print multiple lines, if given more input.
printf "%-6s:%4d\n" Tom 8 Jerry 16 Marry 128

printf "%-6s:%4d\n" Tom 8 Jerry 16 Marry
