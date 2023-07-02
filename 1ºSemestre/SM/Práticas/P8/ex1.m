%% Exercício 1 (Teste da função) Guião 7
clear
clc

load('Guitar03.mat')

figure(2)
plot(x)
MaxDelay = 0.3;
NumComp = 15;
y = chorus(x,fa,MaxDelay, NumComp);
sound(y,fa);
figure(1)
Ta = 1/fa;

[y, fy] = Espetro(y, Ta);