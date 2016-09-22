#!/bin/bash

set -v -x

# Move cursor to top-left of screen.
TopLeft='\e[0;0H'
printf "$TopLeft"

# Clear from cursor position to end of screen.
ClearEOS='\e[J'
printf "$ClearEOS"

# Clear from cursor position to end of line.
ClearEOL='\e[K'
printf "$ClearEOL"


for n in {0..9}
do
	printf "\e[%dm Hello World\n" $n
done


for j in {0..9}
do
	for k in {0..9}
	do
		printf "\e[%d;%dm Hello World\n" $j $k
	done
done
printf "\e[m"
