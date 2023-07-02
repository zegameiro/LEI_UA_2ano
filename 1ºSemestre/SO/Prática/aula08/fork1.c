#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int ret;

    printf("Antes do fork: PID = %d, PPID = %d\n", getpid(), getppid());
    if ((ret = fork()) < 0) { 
        perror ("erro na duplicação do processo");
        return EXIT_FAILURE;
    }
    if (ret > 0) sleep (1);
    printf("Quem sou eu?\nApós o fork: PID = %d, PPID = %d\n", getpid(), getppid());

    return EXIT_SUCCESS;
}
// sleep(1) é usado para que o processo pai não termine antes do filho e o processo filho não termina antes do pai.
// O processo filho é criado com o mesmo código do processo pai, mas com um PID diferente.
// O processo pai é o primeiro a ser executado, e o processo filho é o segundo a ser executado.
// ret é o valor de retorno do fork, se for maior que 0, então é o processo pai, se for igual a 0, então é o processo filho.
// O programa imprime  5 vezes o PID e PPID, 2 vezes para o processo pai e 3 vezes para o processo filho.
