#!/bin/bash

set -v -x

cd()
{
	builtin cd $1
}

cd temp

builtin cd -

dirs

pwd

pwd -P

menu who date "ls:ls -l"
