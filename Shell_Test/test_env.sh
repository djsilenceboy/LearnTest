#!/bin/bash

if [[ ! -v ENABLED ]]; then
    echo "ENABLED is not set."
elif [[ -z "$ENABLED" ]]; then
    echo "ENABLED is set as an empty string."
else
    echo "ENABLED is set with value '$ENABLED'."
fi
