#!/bin/bash

# "Tom Jerry" is non-option parameters.
# -ab Book -c Cake -d Dog -e Tom Jerry

# "-d Dog -e" will be non-option parameters due to after "Tom".
# -ab Book -c Cake Tom -d Dog -e

# "-d Dog -e" will be non-option parameters due to "--".
# "--" means the end of options part.
# -ab Book -c Cake -- -d Dog -e

set -v -x

# The number of all command line parameters.
echo "$#"

# Option string.
OPTSTRING=ab:c:d:e

# Parse all option parameters.
while getopts $OPTSTRING opt
do
	echo $opt "-" $OPTARG
done

# Bypass all options parameters, leave all non-option parameters.
shift $(($OPTIND - 1))

# The number of all non-option command line parameters.
echo "$#"

# All non-option command line parameters.
echo "$@"
