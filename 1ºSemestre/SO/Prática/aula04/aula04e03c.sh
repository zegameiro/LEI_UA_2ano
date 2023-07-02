#!/bin/bash
declare -A assArray

assArray[Aveiro]=10
assArray[Porto]=NA
assArray+=([Lisboa]=5)

echo vals ${assArray[@]}
echo count ${#assArray[@]}
echo index ${!assArray[@]}

echo val Lisboa ${assArray[Lisboa]}