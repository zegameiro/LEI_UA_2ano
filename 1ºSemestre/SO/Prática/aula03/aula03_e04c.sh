# !/bin/bash

if (( $1 < 0 || $1 > 99)); then
    echo "The input number is not valid it must be between 0 and 99"
else
    echo "The input number is valid"
fi