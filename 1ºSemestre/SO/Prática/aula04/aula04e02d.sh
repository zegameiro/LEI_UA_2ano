#!/bin/bash
function compare_numbers() {
    if (($1>$2)); then
        return 0;
    elif (($1<$2)); then
        return 1;
    else
        return 2;
    fi

}
echo "Escreva um número: "
read num1
echo "Escreva outro número: "
read num2

compare_numbers $num1 $num2

case $? in
    0)
        echo "$num1 é maior do que $num2"
        ;;
    1)
        echo "$num1 é menor do que $num2"
        ;;
    2)
        echo "$num1 é igual a $num2"
        ;;
    *)
        echo "Ocorreu um erro"
        ;;
    esac