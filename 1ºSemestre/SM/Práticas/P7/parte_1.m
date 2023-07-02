%% Exercício 1 Guião 6 parte 1

clear
clc

load("Guitar03.mat");
 
sound(x,fa)

%% Exercício 2 Guião 6 parte 1

Ta = 1 / fa;

[X,f] = Espetro(x,Ta);

figure(1)
plot(f,X)
xlim([-4000 4000])
xlabel("Frequência (Hz)")
ylabel("Magnitude")
grid on

%% Exercício 3 Guião 6 parte 1

Hf = zeros(1,length(x));
Hf(f>100 & f<400) = 1;
Hf(f>-400 & f<-100) = 1;

figure(2)
plot(f,Hf)
xlim([-2000 2000])
ylim([0 2])

%% Exercício 3 Guião 6 parte 1

Y = X .* Hf';

figure(3)
plot(f,abs(Y))
xlim([-1000 1000])
grid on

[y,Ta] = Reconstroi(Y,f);

fa = 1 / Ta;
sound(y,fa)

figure(4)
plot(f,x)

%% Exercício 3 para 400 e 600 hz Guião 6 parte 1

Hf1 = zeros(1,length(x));
Hf1(f>400 & f<600) = 1;
Hf1(f>-600 & f<-400) = 1;

figure(5)
plot(f,Hf1)
xlim([-2000 2000])
ylim([0 2])

Y1 = X .* Hf1';

figure(6)
plot(f,abs(Y1))
xlim([-1000 1000])
grid on

[y,Ta] = Reconstroi(Y1,f);

fa = 1 / Ta;
sound(y,fa)

%% Exercício 3 para 600 e 1200 hz Guião 6 parte 1

Hf2 = zeros(1,length(x));
Hf2(f>600 & f<1200) = 1;
Hf2(f>-1200 & f<-600) = 1;

figure(5)
plot(f,Hf2)
xlim([-2000 2000])
ylim([0 2])

Y2 = X .* Hf2';

figure(6)
plot(f,abs(Y2))
xlim([-1000 1000])
grid on

[y,Ta] = Reconstroi(Y2,f);

fa = 1 / Ta;
sound(y,fa)
