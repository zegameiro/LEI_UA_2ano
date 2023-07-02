#include <iostream>
using namespace std;

int main(void) {
    cout << "Escreva um número: " << std::endl; //First output
    int x;
    cin >> x;  // read an integer from the user input
    cout << "Números:\n"; //Second output

    for (int i = 0 ; i <= x ; i++) {
        cout << i << std::endl; //Third output
    }

    return 0;
}