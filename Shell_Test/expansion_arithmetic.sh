#!/bin/bash

set -v

echo $((10 + 2))

echo $((10 - 2))

echo $((10 * 2))

echo $((10 / 2))

echo $((10 % 2))

echo $((!0))

echo $((!1))

echo $((~0))

echo $((~1))

echo $((1 << 4))

echo $((128 >> 4))

echo $((10 & 4))

echo $((10 | 4))

echo $((10 ^ 4))

NUM=1

echo $((NUM++))

echo $((++NUM))

echo $((NUM--))

echo $((--NUM))

echo $((NUM ? 10 : 20))

echo $((NUM--))

echo $((NUM ? 10 : 20))

echo $((1, 2))

echo $((1 != 0))

echo $((1 > 0))
