%% Exercício 1 b) Guião 2 (2.2)

clear
clc

x = [1 2 3 4 5 6];  % Vetor com as possibilidades

px = ones(1,6) / 6; % Matriz de 1's

subplot(1,2,1)
stem(x,px)
xlabel('x')
xlim([0 7])
ylabel('Função massa de probabilidade de X')
grid on

fx = cumsum(px);

subplot(1,2,2)
stairs([0 x 7],[0 fx 1])
xlabel('x')
xlim([0 7])
ylabel('Função de distribuição acumulada de X')
grid on


