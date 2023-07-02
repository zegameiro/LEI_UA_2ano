%% Exercício 2a) Guião 5
clear
clc

Ta = 0.2;
T = 1;
Np = 5;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

[t1,yt] = ReconstroiSinal(xt,Ta);

figure(1)
plot(t1,yt,Color='blue')
hold on
plot(t,xt,".");
legend("Sinal reconstruído","Sinal Original")
grid on

%% Exercício 2b) Guião 3

Ta = 0.02;
T = 1;
Np = 5;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

yt = sin(10 * pi * t) + cos(12 * pi * t) + cos(14 * pi * t - pi / 4);

[t2,zt] = ReconstroiSinal(yt,Ta);

figure(2)
plot(t2,zt,Color='blue')
hold on
plot(t,yt,".");
legend("Sinal reconstruído","Sinal Original")
grid on


