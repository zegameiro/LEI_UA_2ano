%% Exercício 1

clc
clear

load('Guitar02.mat')
fa = 1/Ta;

sound(x, fa)

t = (0:Ta:length(x)*(Ta)-(Ta));

figure(1)
plot(t, x)
xlabel("t (seg)")
ylabel("Amplitude")
grid on
title("Gráfico do exercício 1a)")

fprintf("Duração = %.4f \n",t(length(t)))

%% Exercício 2

[X, f] = Espetro(x, Ta);

figure(2)
plot(f, X)
xlabel("Frequência (Hz)")
ylabel("Magnitude")
title("Espetro do exercício 1 b)")
grid on
xlim([-5e3 5e3])

%% Exercício 3

fa_new = fa/2;

disp("Ao diminuir a frequência para metade," + ...
    "de acordo com o critério de Nyquist, o sinal " + ...
    "vai perder informação e não vai reconstruir-lo em pleno")

sound(x,fa_new)
Ta_new = 1/fa_new;

t_new = (0:Ta_new:length(x)*(Ta_new)-(Ta_new));

figure(3)
plot(t_new, x)
xlabel("Tempo (s)")
ylabel("Novo Sinal")
grid on
title("Fa-new = fa/2")

[X1,f1] = Espetro(x,Ta_new);

figure(4)
plot(f1,X1)
xlabel("Frequência (Hz)")
ylabel("Magnitude")
title("Espetro do Novo Sinal")
grid on

fprintf("Duração do sinal novo = %.4f\n",t_new(length(t_new)))


%% Exercício 4

Hf = zeros(1, length(x));
Hf(f>867 & f<869) = 1;
Hf(f>-869 & f<-867) = 1;
Hf(f>1299 & f<1301) = 1;
Hf(f>-1301 & f<-1299) =1;
Y = Hf'.*X;

figure(5)
plot(f, abs(Y))
xlabel("Frequência (Hz)")
ylabel("Magnitude")
title("Espetro do exercício 1 d)")
xlim([-2000 2000])
grid on

[x2, Ta2] = Reconstroi(Y, f);
sound(abs(x2), fa)

t4 = (0:1:(500-1));
x4 = zeros(500, 1);

for i=1:500
    x4(i, 1) = x2(i);     % Cria um vetor com 500 amostras de X2
end

figure(6)
plot(t4, real(x4), ".-")
xlabel("Tempo (s)")
ylabel("Sinal x(t)")
title("Gráfico do exercício 1 d)")
grid on

%% Exercício 5

x_new = zeros(500, 1);
N = 2^4;  % Número de bits

for i=1:500
    x_new(i,1) = x(i, 1);
end

[partition,codebook] = lloyds(x_new,N);   % A função lloyds calcula a partition e o codebook utilizando com argumentos o vetor de amostras x_new e o número de bits
[indx,quant,distor] = quantiz(x_new,partition,codebook);

t = (0:Ta:length(quant)*(Ta)-(Ta));

figure(7)
plot(t,x_new,t,quant,'--');
legend("Sinal Normal","Sinal Quantizado")
xlabel("Tempo (s)")
ylabel("x_new(t)")
title("Gráfico do exercício 1 e)")
grid on

[t_rec, xt_rec] = ReconstroiSinal(quant,Ta);

figure(8)
plot(t,x_new,t_rec,xt_rec)
legend("Sinal Normal","Sinal Reconstruído")
xlabel("Tempo (s)")
ylabel("Sinal")
grid on