#!/bin/bash

set -v -x

# Not good.

Year=$(date +%Y)
Month=$(date +%m)
Fay=$(date +%d)
Hour=$(date +%H)
Minute=$(date +%M)
Second=$(date +%S)

# Good.

DateTime=$(date +%Y-%m-%dT%H:%M:%S)

Date=${DateTime%T*}
Time=${DateTime#*T}

Year=${Date%%-*}
MouthDay=${Date#*-}
Mouth=${MouthDay%-*}
Day=${MouthDay#*-}

Hour=${Time%%:*}
MinuteSecond=${Time#*:}
Minute=${MinuteSecond%:*}
Second=${MinuteSecond#*:}

# Best.

eval "$(date "+Year=%Y Mount=%m Day=%d Hour=%H Minute=%M Second=%S")"
