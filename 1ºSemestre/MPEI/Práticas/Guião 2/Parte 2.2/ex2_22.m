%% Exercício 2 b) Guião 2 (2.2)

clear
clc

X = [5 50 100];       % Variável aleatória X
pX = [0.9 0.09 0.01]; % Probabilidade da variável aleatória X

subplot(1,2,1)
stem(X,pX)
xlabel('x')
ylabel('Função massa de probabilidade de X')
grid on

%% Exercício 2 c) Guião 2 (2.2)

fX = cumsum(pX);

subplot(1,2,2)
stairs([0 X 105], [0 fX 1])
xlabel('x')
ylabel('Função distribuição acumulada de X')
