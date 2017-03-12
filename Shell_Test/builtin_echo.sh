#!/bin/bash

set -v -x

echo "This is 'Hello World'."

echo "This is \"Hello World\"."

echo 'This is "Hello World".'

# Single quote cannot in single quote.
# echo 'This is \'Hello World\'.'
# Try this:
echo 'This is '"'"'Hello World'"'"'.'

Var=abc
echo ${Var}_2
echo $VAR_2

echo $':'
