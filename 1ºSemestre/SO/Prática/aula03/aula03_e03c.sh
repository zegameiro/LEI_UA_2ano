#!/bin/bash
# Testa se ano dado (ou ano actual, se nenhum for dado)
# é bissexto ou comum.
if [[ $# = 1 ]]; then
    year=$1
else
    year=$(date +%Y)
fi
if [[ $(($year % 400)) -eq "0" ]]; then
    echo "Ano bissexto. Fevereiro tem 29 dias."
elif [[ $(($year % 4)) -eq 0 ]]; then
if [[ $(($year % 100)) -ne 0 ]]; then
    echo " Ano bissexto. Fevereiro tem 29 dias."
else
    echo "Ano comum. Fevereiro tem 28 dias."
fi
else
    echo " Ano comum. Fevereiro tem 28 dias."
fi