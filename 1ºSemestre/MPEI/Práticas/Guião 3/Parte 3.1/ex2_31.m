%% Exercício 2a) Guião 3 (3.1)
clear
clc

disp("a) -------------------")

Tij =  [1/3 1/4 0;
        1/3 11/20 1/2;
        1/3 1/5 1/2]

if(sum(Tij) == 1)
    disp("A matriz é estocástica pois a soma das colunas é igual a 1")
else
    disp("A matriz não é estocástica \n")
end

%% Exercício 2b) Guião 3 (3.1)

disp("b) -------------------")

% Nº total de alunos = 90
% Nº alunos A = Nº alunos B + Nº alunos C
% Nº alunos B = Nº alunos C

v = [1/2 ; 1/4 ; 1/4]

%% Exercício 2c) Guião 3 (3.1)

disp("c) -------------------")

x = Tij^29 * v;
x = x .* 90;
fprintf(" O grupo A contém aproximadamente %.2f alunos \n O grupo B contém aproximadamente %.2f alunos \n O grupo C contém aproximadamente %.2f alunos \n \n",x)

%% Exercício 2d) Guião (3.1)

disp("d) -------------------")
v = [1/3 ; 1/3 ; 1/3];

x2 = Tij^29 * v;
x2 = x2 .* 90;
fprintf(" O grupo A contém aproximadamente %.2f alunos \n O grupo B contém aproximadamente %.2f alunos \n O grupo C contém aproximadamente %.2f alunos \n \n",x2)

