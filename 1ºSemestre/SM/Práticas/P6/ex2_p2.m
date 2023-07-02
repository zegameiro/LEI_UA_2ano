%% Exercício 2  para nbit = 2 Guião 5 Parte 2

clear
clc

Ta = 0.02;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 2;  % Número de bits
Npal = 2^nbit;

[X,f] = Espetro(xt,Ta);

figure(1)
stem(f,X)

delta = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx,quant] = quantiz(xt,partition,codebook);

[X1,f1] = Espetro(quant,Ta);

figure(2)
stem(f1,X1)

%% Exercício 2  para nbit = 3 Guião 5 Parte 2

clear
clc

Ta = 0.02;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 3;  % Número de bits
Npal = 2^nbit;

delta1 = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx1,quant] = quantiz(xt,partition,codebook);

[X3,f3] = Espetro(quant,Ta);

figure(3)
stem(f3,X3)

%% Exercício 2  para nbit = 4 Guião 5 Parte 2

clear
clc

Ta = 0.02;
T = 1;
Np = 2;
N = (T / Ta) * Np;
t = (0:N-1) * Ta;

xt = sin(2 * pi * t);

nbit = 4;  % Número de bits
Npal = 2^nbit;

delta3 = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx3,quant] = quantiz(xt,partition,codebook);

[X4,f4] = Espetro(quant,Ta);

figure(4)
stem(f4,X4)
