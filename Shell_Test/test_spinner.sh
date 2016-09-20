#!/bin/bash

Spinner="\|/-"
until read -sn1 -t0.5 -p "Press any key..." Var
do
	printf " %s\r" ${Spinner:0:1}
	temp=${Spinner#?}
	Spinner=$temp${Spinner%"$temp"}
done
