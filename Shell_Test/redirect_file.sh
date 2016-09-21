#!/bin/bash

set -v -x

LogFile=$TMP/temp.log
ErrorLogFile=$TMP/temp_error.log

# FD0 equals to file "/dev/fd/0".
# FD1 equals to file "/dev/fd/1".
# FD2 equals to file "/dev/fd/2".

echo > $LogFile
echo > $ErrorLogFile

{
	echo "Step 0.1"
	echo "Step 0.2"
} > $LogFile

# FD1 to file by default.
# FD2 not to file by default.
echo "Step 1" >> $LogFile
printf "%v\n" OK >> $LogFile
echo >> $LogFile

# FD1 to file by default.
# FD2 to another file.
echo "Step 2" >> $LogFile
printf "%w\n" OK >> $LogFile 2>> $ErrorLogFile
echo >> $LogFile

# FD2 to FD1 (not file).
# FD1 to file by default.
echo "Step 3" >> $LogFile
printf "%y\n" OK 2>&1 >> $LogFile
echo >> $LogFile

# FD1 to file by default.
# FD2 to new FD1 (to file).
echo "Step 4" >> $LogFile
printf "%v\n" OK >> $LogFile 2>&1
echo >> $LogFile

# FD1 and FD2 to same file.
# "&>>" means ">> 2>&1"
echo "Step 5" >> $LogFile
printf "%w\n" OK &>> $LogFile
echo >> $LogFile

# FD1 to file by default.
# FD2 to new FD1 (to file).
exec >> $LogFile 2>&1
# This line can split in two line.
# exec >> $LogFile
# exec 2>&1
echo "Step 6"
printf "%y\n" OK
