#!/bin/bash

#
# ''
# x
# '' ''
# x ''
# x x

set -v -x

# "?"
# Return error message and exit, when unset.

echo ${1?1st is unset}

echo ${2?2nd is unset}

# ":?"
# Return error message and exit, when unset or set but empty.

echo ${1:?1st is unset or empty}

echo ${2:?2nd is unset or empty}
