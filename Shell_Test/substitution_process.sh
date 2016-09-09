#!/bin/bash

set -v

# "<()" and ">()" can be used in place of pipeline "|".

# "<()" is same as a temp input file.
pr -T < <(ls -l r*)

# ">()" is same as a temp output file.
ls -l r* > >(pr -T)

# ">()" is similar to pipeline.
ls -l r* | pr -T
