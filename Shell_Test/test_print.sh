#!/bin/bash

set -v

echo "This is 'Hello World'."

echo "This is \"Hello World\"."

echo 'This is "Hello World".'

# Single quote cannot in single quote.
# echo 'This is \'Hello World\'.'

VAR=abc
echo ${VAR}_2
echo $VAR_2

echo $':'
