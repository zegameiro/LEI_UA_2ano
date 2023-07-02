#!/bin/bash
# This script checks the existence of a file
echo "Checking..."
if [[ $# -eq 0 ]] ; then
    echo "Nenhum ficheiro especificado"
    exit 0
fi
if [[ -d $1 ]] ; then
    echo "$1 existe."
else
    echo "$1 não existe"
fi
echo "...done."