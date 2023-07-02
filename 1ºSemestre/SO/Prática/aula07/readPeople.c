#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

#define MAX_PEOPLE 100
#define MAX_NAME_LENGTH 64

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
    Person ps[1024];
    Person p;
    int answer;
    Person people_array[MAX_PEOPLE] = {
        {.age = -1},
        {.name = "N/A"},
        {.height = 0}
    };

    /* Validate number of arguments */
    if(argc != 2)
    {
        printf("USAGE: %s fileName\n", argv[0]);
        return EXIT_FAILURE;
    }

    /* Open the file provided as argument */
    errno = 0;
    fp = fopen(argv[1], "rb");
    if(fp == NULL)
    {
        perror ("Error opening file!");
        return EXIT_FAILURE;
    }
    /* read all the itens of the file */
    int n_people = 0;
    while(fread(&p, sizeof(Person), 1, fp) == 1)
    {
        printPersonInfo(&p);
        people_array[n_people++] = p;
    }

    fclose(fp);
    
    
    printf("Would you like to add a person? (1=yes, 0=no)\n");
    scanf(" %d", &answer);

    if(answer == 1) {
        fp = fopen(argv[1], "ab");
        int n;
        printf("How many people?\n ");
        scanf(" %d",&n);

        if(n + n_people > MAX_PEOPLE) {
            printf("Too many people!\n");
            return EXIT_FAILURE;
        }

        char name[MAX_NAME_LENGTH];
        int age;
        double height;

        for(int i = 0; i < n; i++) {
            printf("\nPerson %d\n", i+1);

            printf("Name?\n");
            scanf(" %[^\n]", name);

            printf("Age?\n");
            scanf("%d", &age);

            printf("Height?\n");
            scanf("%lf", &height);

            strcpy(p.name,name);
            p.age = age;
            p.height = height;
            fwrite(&p, sizeof(Person), 1, fp);
        }

        while(fread(&p, sizeof(Person), 1, fp) == 1) {
            printPersonInfo(&p);
            people_array[n_people++] = p;
        }

    fclose(fp);

    } else if(answer == 0) {
        printf("\nNothing changed \n");
        /* read all the itens of the file */

        return EXIT_SUCCESS;

    } else {
        printf("Invalid answer\n");
    }
}
