#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    unsigned int i;

    printf("PID = %u\n", getpid());
    i = 0;
    while (i <= 10) { 
        printf("\r%08u ", i++);
        fflush(stdout);  // fflush() limpa o buffer de saída. stdout é o buffer de saída padrão.
        sleep(1); 
    }
    printf("\n");

    return EXIT_SUCCESS;
}
