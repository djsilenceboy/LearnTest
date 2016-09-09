#!/bin/bash

set -v

if [ 1 = 1 ]; then
	echo "Step 1"
fi

if [ 1 = 1 ]
then
	echo "Step 2"
fi

if ((1 > 0)); then
	echo "Step 3"
fi

case "Yes" in
	*e*) echo "Step 4.1";;
	*) echo "Step 4.2";;
esac

case "A747" in
	A[0-9]*) echo "Step 5.1";;
	*) echo "Step 5.2";;
esac
