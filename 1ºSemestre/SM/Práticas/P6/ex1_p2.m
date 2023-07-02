%% Exercício 1 para nbit = 2 Guião 5 parte 2

clear
clc

Ta = 0.002;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 2;  % Número de bits
Npal = 2^nbit;

delta = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx,quant] = quantiz(xt,partition,codebook);

figure(1)
plot(t,xt)
hold on
plot(t,quant,"--")
grid on

%% Exercício 1 para nbits = 3 Guião 5 parte 2

Ta = 0.002;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 3;  % Número de bits
Npal = 2^nbit;

delta2 = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx1,quant1] = quantiz(xt,partition,codebook);

figure(2)
plot(t,xt)
hold on
plot(t,quant1,"--")
grid on

%% Exercício 1 para nbit = 4 Guião 5 parte 2

Ta = 0.002;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 4;  % Número de bits
Npal = 2^nbit;

delta3 = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx2,quant2] = quantiz(xt,partition,codebook);

figure(3)
plot(t,xt)
hold on
plot(t,quant2,"--")
grid on