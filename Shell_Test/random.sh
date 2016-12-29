#!/bin/bash

set -v -x

echo $RANDOM

echo $RANDOM

echo $RANDOM

echo $RANDOM

echo `cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1`
