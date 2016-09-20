#!/bin/bash

set -v -x

cd()
{
	builtin cd $1
}

cd temp


pwd

pwd -P
