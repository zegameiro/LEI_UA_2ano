%% Guião 4 exercício 4 para o 3a) 

clear
clc

T = 1;
Np = 4;
Ta = 0.001;
N = (T / Ta) * Np;
t = (0:N-1)*Ta;
x = sin(2*pi.*t);

[X, f] = Espetro(x,Ta);

[x1,Ta1] = Reconstroi(X,f);
plot(t,x1,LineStyle=)
