#!/bin/bash
# Agrupamento de comandos na Bash
{
i=0
while read line; do
    echo $i: $line
    i=$(($i+1))
done
} < $1