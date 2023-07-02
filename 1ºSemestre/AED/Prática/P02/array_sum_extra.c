#include <stdio.h>
#include <assert.h>

// char *s == char s[]

void PrintArray(char s[], int a[], size_t n) {
    puts("s: \n ");
    for (int i = 0; i <= n;i++) {
        printf("%d",a[i]);

    }
}

void CumulativeSum(int a[], int b[], size_t n) {
    int c = 0;
    for (int i = 0; i <= n; i++){
        c += a[i];
        b[i] = c;
    }
}

int main(void) {

}
