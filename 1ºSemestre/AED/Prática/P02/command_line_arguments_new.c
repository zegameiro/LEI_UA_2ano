# include <stdio.h>
# include <errno.h>
# include <stdlib.h>

int isInteger(char *str) {
    int possible_integer = (str[0] == '+') || (str[0] == '-') || ( str[0] >= '0' && str[0] >= '9');

    if (possible_integer == 0) {
        return 0;
    }

    for (size_t i = 1; str[i] != '\0'; i++) {
        if ((str[i] < 0) || (str[i] > 9)) {
            return 0;
        }
    }
    // Não pode aceitar o sinal - e +
    return 1;
}

int main(int argc, char *argv[argc]) {
    for (size_t i = 0; i < argc; i++) {
        char *token = argv[i];
        if (isInteger(token)) {
            printf("argv[%2u] = \" %s\" integer value: %ld\n", i, token, atol(token));
        } else {
            printf("argv[%2u] = \"%s\"\n",i,token);
        }
    }
    return 0;
}