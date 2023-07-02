%% Exercício 3 para o exercício 2a) com nbit = 2 Guião 5 parte 2

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

delta = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx,quant] = quantiz(xt,partition,codebook);

figure(1)
plot(t,xt)
xlabel("Tempo (s)")
ylabel("Função x(t)")
grid on
hold on

[t,yt] = ReconstroiSinal(quant,Ta);

plot(t,yt)

%% Exercício 3 para o exercício 2a) com nbit = 3 Guião 5 parte 2

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

[indx1,quant1] = quantiz(xt,partition,codebook);

[t1,yt1] = ReconstroiSinal(quant1,Ta);

plot(t1,yt1)

%% Exercício 3 para o exercício 2a) com nbit = 4 Guião 5 parte 2

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

delta2 = 2 / Npal;

[partition, codebook] = lloyds(xt,Npal);

[indx2,quant2] = quantiz(xt,partition,codebook);

[t2,yt2] = ReconstroiSinal(quant2,Ta);

plot(t2,yt2)
legend("Sinal normal","Sinal quantizado para nbit = 2","Sinal quantizado para nbit = 3","Sinal quantizado para nbit = 4")
hold off