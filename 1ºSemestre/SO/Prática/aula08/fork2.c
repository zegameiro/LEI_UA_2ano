#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int ret;

    printf("Antes do fork: PID = %d, PPID = %d\n", getpid(), getppid());
    if ((ret = fork()) < 0) { 
        perror("erro na duplicação do processo");
        return EXIT_FAILURE;
    }
    if (ret > 0) sleep(1);

    printf("Quem sou eu?\nApós o fork: PID = %d, PPID = %d, retorno do fork = %d\n",getpid(), getppid(), ret);

    if (ret == 0) {
        printf("Sou o processo FILHO\n");
    } else {
        printf("Sou o processo PAI\n");
    }

    return EXIT_SUCCESS;
}

// O valor de retorno do fork é o PID do processo filho para o processo pai, e 0 para o processo filho.

