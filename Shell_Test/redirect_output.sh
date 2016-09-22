#!/bin/bash

set -v -x

# Pipeline is in sub-shell.
echo "192.168.10.25" | while IFS=. read A B C D
do
	echo $A $B $C $D
done

echo $(tty)
[ -t /dev/pty1 ] && echo "Hello, this is $(tty)." > /dev/pty1
