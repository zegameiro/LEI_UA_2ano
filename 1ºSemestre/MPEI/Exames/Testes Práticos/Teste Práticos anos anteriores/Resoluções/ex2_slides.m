%% Exercício 2a) MPEI slides MPEI-2020-2021-Exercicios2.pdf
clear
clc

%        Iraque   França   Suíça   Brasil   EUA   Israel
T_can = [   0.7 ,    0.1 ,     0 ,      0 ,   0 ,      0 ;  % Iraque   
            0.2 ,      0 ,   0.3 ,      0 ,   0 ,      0 ;  % França
              0 ,    0.6 ,   0.1 ,      0 ,   0 ,      0 ;  % Suíça
            0.2 ,    0.3 ,   0.4 ,    0.1 ,   0 ,      0 ;  % Brasil
              0 ,      0 ,   0.2 ,    0.4 ,   1 ,      0 ;  % EUA
              0 ,      0 ,     0 ,    0.5 ,   0 ,      1 ]; % Israel

v = [0 ; 0.5 ; 0.5 ; 0 ; 0 ; 0];

Iraque = 1;
Franca = 2;
Suica = 3;
Brasil = 4;
EUA = 5;
Israel = 6;

%% Exercício 2b) MPEI slides MPEI-2020-2021-Exercicios2.pdf

x0 = [1 ; 0 ; 0 ; 0 ; 0 ; 0];
n = 10; % Definir um número máximo de meses
total = 0;

% For loop para calcular a probabilidade de levar x meses a chegar a Israel
% ou aos EUA
for x = 1:n
    P = T_can^x * x0;

    % Soma x * P[5] E X * P[6] e armazena o resultado em total
    total = total + x * P(EUA) + x * P(Israel);
end

fprintf("O número de meses médio necessários para um terrorista terminar os seus dias em Israel ou nos EUA será %.4f\n",total)

%% Exercício 2c) MPEI slides MPEI-2020-2021-Exercicios2.pdf

x0 = [1 ; 0 ; 0 ; 0 ; 0 ; 0]; % Neste caso o terrorista terá começado no Iraque

prob = T_can^5 * x0;

fprintf("A probabilidade de se encontrar nos EUA passado 5 meses é de %.4f\n",prob(EUA))

x0 = [0 ; 0 ; 0 ; 1 ; 0 ; 0]; % Neste caso o terrorista terá começado no Brasil

prob_1 = T_can^50 * x0;

fprintf("A probabilidade de estar em Israel 50 meses depois de ter estado no Brasil é de %.4f\n",prob_1(Israel))

