#!/bin/bash

if [[ -z $1 ]];then
    echo "Missing argument: requires file";
    exit 1;
fi
python3 $1