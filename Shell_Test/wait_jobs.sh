#!/bin/bash

set -v -x

for i in {0..4}
do
	sleep 10 &
	PIDS[i]=$!
	echo "A: Start: #$i = ${PIDS[i]}"
done

jobs

for PID in ${PIDS[@]}
do
	wait $PID
	EXIT_CODE=$?
	echo "A: Stop: $PID with $EXIT_CODE."
done

echo "A: All stopped."

jobs

function inner()
(
	sleep 10
)

for i in {0..4}
do
	inner &
	PIDS[i]=$!
	echo "B: Start: #$i = ${PIDS[i]}"
done

jobs

for PID in ${PIDS[@]}
do
	wait $PID
	EXIT_CODE=$?
	echo "B: Stop: $PID with $EXIT_CODE."
done

echo "B: All stopped."

jobs
