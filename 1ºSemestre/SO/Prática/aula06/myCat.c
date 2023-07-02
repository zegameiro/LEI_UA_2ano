#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

/* SUGESTÂO: utilize as páginas do manual para conhecer mais sobre as funções usadas:
 man fopen
 man fgets
*/

#define LINEMAXSIZE 800 /* or other suitable maximum line size */


int main(int argc, char *argv[])
{
    FILE *fp = NULL;
    char line [LINEMAXSIZE]; 

    /* Validate number of arguments */
    // if( argc != 2 )
    // {
    //     printf("USAGE: %s fileName\n", argv[0]);
    //     return EXIT_FAILURE;
    // }
    
    /* Open the file provided as argument */

    errno = 0;
    
    for (int i = 1; i < argc; i++) {
        fp = fopen(argv[i],"r");
        int count = 1;

        if( fp == NULL ) {
            perror ("Error opening file!");
            return EXIT_FAILURE;
        }

        while( fgets(line, sizeof(line), fp) != NULL )
        {
            printf("%-3d -> %s", count,line); /* not needed to add '\n' to printf because fgets will read the '\n' that ends each line in the file */
            count++;
        }

        puts("\n----------------------------------------\n");

        fclose(fp);
    }

    return EXIT_SUCCESS;
}
