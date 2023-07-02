#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

typedef struct
{
    int age;
    double height;
    char name[64];
} Person;

void printPersonInfo(Person *p)
{
    printf("Person: %s, %d, %f\n", p->name, p->age, p->height);
}

int main (int argc, char *argv[])
{
    FILE *fp = NULL;
    int i;
    Person p = {35, 1.65, "xpto"};

    /* Validate number of arguments */
    if(argc != 2)
    {
        printf("USAGE: %s fileName\n", argv[0]);
        return EXIT_FAILURE;
    }

    /* Open the file provided as argument */
    errno = 0;
    fp = fopen(argv[1], "wb");
    if(fp == NULL)
    {
        perror ("Error opening file!");
        return EXIT_FAILURE;
    }

    int nPeople;

    printf("How many people do you want to write?\n ");
    scanf("%d", &nPeople);

    for(i = 0; i < nPeople; i++)
    {   
        printf("\nPerson #%d\n",i+1);

        printf("Name?\n");
        scanf(" %[^\n]", &p.name);

        printf("Age?\n");
        scanf("%d", &p.age);

        printf("Height?\n");
        scanf("%lf", &p.height);

        fwrite(&p, sizeof(Person), 1, fp);

        printf("\n");
    }

    /* Write 10 itens on a file */
    // for(i = 0 ; i < 10 ; i++)
    // {    
    //     p.age = p.age+1;
    //     p.height = p.height+0.03;
    //     fwrite(&p, sizeof(Person), 1, fp);
    // }

    fclose(fp);

    return EXIT_SUCCESS;
}
