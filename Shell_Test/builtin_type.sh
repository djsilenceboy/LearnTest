#!/bin/bash

set -v -x

# Match built-in first, then file.
type echo ls printf time

# Match all built-in and file.
type -a echo ls printf time

# Match built-in first, then file.
# But show file only.
type -p echo ls printf time

# Match all built-in and file.
# But show file only.
type -P echo ls printf time
