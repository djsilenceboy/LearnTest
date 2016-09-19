#!/bin/bash

set -v

echo *

echo t*

echo com*

echo ?o*

echo ?[ah]*

echo [r-t]*

# Normally, when no file name matches pattern, just display pattern.
echo x*y

# Enable "nullglob", when no file name matches pattern, display empty.
shopt -s nullglob
echo x*y
shopt -u nullglob

# Enable "failglob", when no file name matches pattern, display error message:
#   no match: x*y
shopt -s failglob
echo x*y
shopt -u failglob

# Disable pathname expansion.
set -f

echo *
