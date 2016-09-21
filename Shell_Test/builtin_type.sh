#!/bin/bash

set -v -x

# Match built-in first, then file.
type cp echo ls printf time

# Match all built-in and file.
type -a cp echo ls printf time

# Match built-in first, then file.
# But show file only.
type -p cp echo ls printf time

# Match all built-in and file.
# But show file only.
type -P cp echo ls printf time

# Only display type field.
type -t cp echo ls printf time
