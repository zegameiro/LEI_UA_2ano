%% Aula 1 22/09/2022
% Exercício 1: Gere uma sequência de números impares entre 1 e 10
x = 1:10

% Exercício 2: Gere uma sequência de 11 números inteiros entre -5 e 5.(resolva de 2 formas)
y = -5:1:5
z = linspace(-5,5,11)

% Exercício 3: Gere uma matriz A
A = [1 5 1-j; 4 j -1]

% a)Acrescente uma nova linha e coluna à matriz A

B = [2 4-i];
C = [4 6-i 1+i 9];
A(:,4) = B
A(3,:) = C

% b)Apague as colunas impares. 
D = A(:,1:2:4)

% Exercício 4 -  Suponha que pretendia visualizar o gráfico da seguinte função y = sin(x) * e^x 
% quando a variável x pertence a [-20, 0] (considere 200 elementos para o vetor x 
% distribuídos uniformemente no intervalo indicado)

x = linspace(-2*pi,0,100);
fx = sin(x) .* exp(x);
plot(x,fx,'r:',LineWidth=3)
axis([-8 0 -0.5 0.8])
title('f(x) = sen(x) * exp(x)')
xlabel('x')
ylabel('y')




