#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[]) 
{
    double num1, num2, result;
    char operator;
    char *pnum1, *pnum2;

    if(argc != 4) {
        printf("Arguments exceded. Maximum is 3.\n");
        return EXIT_FAILURE;
    } else {
        num1 = strtod(argv[1],&pnum1);
        operator = argv[2][0];
        num2 = strtod(argv[3],&pnum2);

        switch (operator)
        {
        case '+':
            result = num1 + num2;
            printf("%0.1f + %0.1f = %0.1f \n", num1, num2, result);
            break;

        case '-':
            result = num1 - num2;
            printf("%0.1f - %0.1f = %0.1f \n", num1, num2, result);
            break;  

        case 'x':
            result = num1 * num2;
            printf("%0.1f x %0.1f = %0.1f \n", num1, num2, result);
            break;

        case '/':
            result = num1 / num2;
            printf("%0.1f / %0.1f = %0.1f \n", num1, num2, result);
            break;

        case 'p':
            result = pow((double)num1,num2);
            printf("%0.1f p %0.1f = %0.1f \n", num1, num2, result);
            break;

        default:
            printf("Invalid operation!");
            break;
        }
    }
    return EXIT_SUCCESS;
}