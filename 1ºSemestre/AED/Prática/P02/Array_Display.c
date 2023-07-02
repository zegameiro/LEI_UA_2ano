#include <stdio.h>
#include <assert.h>
#include <stdlib.h>

void display_array(int *a, size_t n) {
    assert(a != NULL &&  n > 0);
    
    printf("Array = [ ");

    for (size_t i = 0; (i < n - 1); i++) {
        printf("%d, ", a[i]);
    }          
    printf("%d ]\n", a[n - 1]);
}

int* read_array(size_t size) {
    assert(size != NULL);

    size_t n;
    printf("Array size(> 0): ");
    scanf("%u", &n);
    assert(n > 0);

    int* array = malloc(n * sizeof(int));  // atribuição de memória ao array com a função memory allocation (malloc) aceita como argumento o número de bits necessário para guardar um número inteiro
    if (array ==  NULL) {               
        *size = 0;      // verificação de erro na alocação de memória
        return NULL;    
    }

    for (size_t i = 0; i < n; i++) {
        printf("array[%2u] = ", i);
        scanf("%d", &array[i]);
    }

    *size = n;
    return array;
}

int* append_array(int* array1, size_t size1, int*array2, size_t size2) {
    assert(array1 != NULL && size1 > 0);
    assert(array2 != NULL && size2 > 0);

    size_t n = size1 + size2;

    int* result = malloc(n * sizeof(int));
    if(result == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < size1; i++) {
        result[i] = array1[i];
    }

    for (size_t i = 0; i < size2; i++) {
        result[size1 + i] = array2[i];
    }

    return result;
}

int main(void) {
    size_t size1, size2;
    int* array1 = read_array(size1);
    int* array2 = read_array(size2);

    display_array(array1, size1);
    display_array(array2, size2);

    int* result = append_array(array1, size1, array2, size2);
    display_array(result, size1 + size2);

    free(array1);
    free(array2);
    free(result);

    return 0;
}


