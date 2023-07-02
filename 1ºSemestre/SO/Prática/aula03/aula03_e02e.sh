#!/bin/bash
# Conditional block if
if (($1>5)) && (($1<10)) ; then
echo "O número é maior que 5 e menor que 10"
else
echo "O número está fora do intervalo [5,10]"
fi
