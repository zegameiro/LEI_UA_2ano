#include <stdio.h>

void f1(int a, int b) {
    a = 10;
    b = 100;
}

void f2(int *a, int *b) {
    *a = 1000;
    *b = 10000;
}

int main(void) {
    int x = 0;
    int y = 1;
    f1(x, y);
    printf("x = %d, y = %d",x,y);

    f2(&x, &y);
    printf("x = %d, y = %d",x,y);

    return 0;