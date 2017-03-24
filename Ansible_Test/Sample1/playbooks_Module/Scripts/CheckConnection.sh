#!/bin/bash

echo > /dev/tcp/$1/$2
if [ $? -eq 0 ]; then
	echo "Connection is ok."
else
	echo "Connection is failed."
fi
