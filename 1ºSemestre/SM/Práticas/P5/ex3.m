%% Guião 4 exercício 3 a)

clear
clc

T = 1;
Np = 4;
Ta = 0.01;
N = (T / Ta) * Np;
t = (0:N-1)*Ta;
x = sin(2*pi.*t);

[X, f] = Espetro(x,Ta);

figure(1)
stem(f,X)
xlabel('Frequência (hz)')
ylabel('Amplitude')
title("Exercício 3a)")
grid on

%% Guião 4 exercício 3b) 
clear
clc
T = 1;
Ta = 0.01;
Np = 4;
N = (T / Ta) * Np;
t = (0:N-1)*Ta;

y = sin(10 * pi .* t) + cos(12 * pi .* t) + cos(14 * pi .*t - pi/4);

[Y, f] = Espetro(y,Ta);

figure(2)
stem(f,Y)
xlabel('Frequência (hz)')
ylabel('Amplitude')
title("Exercício 3b)")
grid on

%% Guião 4 exercício 3c) 
clear
clc
T = 1 / 10;
Ta = 0.001;
Np = 4;
N = (T / Ta) * Np;
t = (0:N-1)*Ta;

y = 10 + 14 * cos(20 * pi .* t - pi / 3) + 8 * cos(40 * pi .*t + pi/2);

[Y, f] = Espetro(y,Ta);

figure(3)
stem(f,Y)
xlabel('Frequência (hz)')
ylabel('Amplitude')
title("Exercício 3c)")
grid on

%% Guião 4 exercício 3d) 
clear
clc
T = 1;
Ta = 0.001;
Np = 5;
N = T / Ta * Np;
t = (0:N-1)*Ta;

w = (square(2 * pi * 1 * t) + 1) / 2;

figure(1)
plot(t,w)
xlim([0,5])
ylim([-0.5, 1.5])
xlabel("Tempo (s)")
ylabel("Função square")

[W, f] = Espetro(w,Ta);

figure(2)
stem(f,W)
xlabel('Frequência (hz)')
ylabel('Amplitude')
title("Exercício 3d)")
grid on

%% Guião 4 exercício 3e)

clear
clc
T = 1;
Ta = 0.01;
Np = 4;
N = (T / Ta) * Np;
t = (0:N-1)*Ta;

q = sawtooth(2 * pi * 1 * t);

figure(1)
plot(t,q)
xlabel("Tempo (s)")
ylabel("Função square")

[Q, f] = Espetro(q,Ta);

figure(2)
stem(f,Q)
xlabel('Frequência (hz)')
ylabel('Amplitude')
title("Exercício 3e)")
grid on
