#!/bin/bash

set -v -x

# Add entry manually.
history -s Hello
history -s World

history

# "-c" to clear all history.
history -c

history
