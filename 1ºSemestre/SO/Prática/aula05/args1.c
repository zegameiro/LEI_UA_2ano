#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    int i;

    if (argc > 2 ) {
        printf("Too many arguments have been inputed. Maximum is 1.\n");
    } else {
        for(i = 0 ; i < argc ; i++)
        {
            printf("Argument %02d: \"%s\"\n", i, argv[i]);        
        }
    }

    return EXIT_SUCCESS;
}
