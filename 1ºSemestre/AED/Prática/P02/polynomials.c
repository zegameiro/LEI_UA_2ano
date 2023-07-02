#include <stdio.h>
#include <assert.h>
#include <stdlib.h>

void DisplayPol(double * coef, unsigned int degree) {
    assert(coef != NULL && degree > 0);
    printf("P(x) = ");

    for (unsigned int i = 0; i < degree; i++) {
        printf("%g x^%u + ", coef[i], i);
    }

    printf("%g x^%u\n", coef[degree], degree);
}

double ComputePol(double* coef, unsigned int degree, double x) {
    assert(coef != NULL && degree > 0);
    double result = 0;

    for (unsigned int i = 0; i <= degree; i++) {
        result += coef[i] * pow(x, i);
    }
    
    return result;
}

unsigned int GetRealRoots(double* coef, unsigned int degree, double*root1, double* root2) {
    assert(coef != NULL && degree > 0);
    assert(root1 != NULL && root2 != NULL);

    double a = coef[2];
    double b = coef[1];
    double c = coef[0];

    double delta = b * b - 4 * a * c;

    if (delta < 0) {
        return 0;
    } else if (delta == 0) {
        *root1 = -b / (2 * a);
        return 1;
    } else {
        *root1 = (-b + sqrt(delta)) / (2 * a);
        *root2 = (-b - sqrt(delta)) / (2 * a);
        return 2;
    }
}

int main(void) {
    double coef[3] = {1, 2, 1};
    double root1, root2;
    unsigned int n = GetRealRoots(coef, 2, &root1, &root2);
    printf("n = %u, root1 = %g, root2 = %g\n", n, root1, root2);
    return 0;
    
}