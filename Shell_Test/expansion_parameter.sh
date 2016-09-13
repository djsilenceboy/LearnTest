#!/bin/bash

set -v

# ":="
# Return default value, when unset or set but empty.
# And also change variable.

unset Var
echo ${Var:=123}
echo $Var

Var=
echo ${Var:=123}
echo $Var

# "="
# Return default value, when unset.
# And also change variable.

Var=
echo ${Var=123}
echo $Var

unset Var
echo ${Var=123}
echo $Var


# ":-"
# Return default value, when unset or set but empty.
# But not change variable.

echo ${Var:-123}
echo $Var

Var=
echo ${Var:-123}
echo $Var

# "-"
# Return default value, when unset.
# But not change variable.

Var=
echo ${Var-123}
echo $Var

unset Var
echo ${Var-123}
echo $Var


# ":+"
# Return alternative value, when set and not empty.
# Otherwise, return original value.
# But not change variable.

unset Var
echo ${Var:+123}
echo $Var

Var=
echo ${Var:+123}
echo $Var

Var=abc
echo ${Var:+123}
echo $Var

# "+"
# Return alternative value, when set.
# Otherwise, return original value.
# But not change variable.

Var=
echo ${Var+123}
echo $Var

Var=abc
echo ${Var+123}
echo $Var

unset Var
echo ${Var+123}
echo $Var
