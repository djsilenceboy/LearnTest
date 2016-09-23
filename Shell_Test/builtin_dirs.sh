#!/bin/bash

set -v -x

# An array DIRSTACK is used to keep directory stack.

# Display DIRSTACK.
dirs -v

# Or display it directly.
echo "${DIRSTACK[@]}"

# Add a new directory into stack.
pushd /home
pushd .
pushd ..
pushd ~

# The folder must be existing.
pushd /NoExisting

dirs -v

# "-l" will expend ".", ".." and "~", etc.
dirs -v -l

# Pop a directory from DIRSTACK.
# And then use "cd" to change to that directory.
# popd

# "-n" to removes the nth entry from bottom of DIRSTACK. Index from 0 for bottom. 
# popd -0
