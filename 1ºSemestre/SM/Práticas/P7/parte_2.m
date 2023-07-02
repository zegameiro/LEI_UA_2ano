%% Exercício 1 Guião 6 parte 2
clc
clear

Ta = 0.001;
N = 10000;

[xt,t] = GeraSinal(N,Ta);

x = sin(2 * pi * t);

figure(1)
plot(t,xt,"b")
xlabel("Tempo (s)")
ylabel("Sinal")
hold on

plot(t,x,"r",LineWidth=2)
legend("Filtrado","Original")
grid on

[X,f] = Espetro(xt,Ta);

figure(2)
plot(f,X)
xlim([-25 25])
xlabel("Frequência Hz")
ylabel("Magnitude")
grid on
