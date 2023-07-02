%% Exercício 2 Guião 3
clc
clear

f0 = 1;
Ta = 0.001;
Np = 2;
bk = [0 4/pi 0 4/(3*pi) 0 4/(5*pi) 0 4/(7 * pi)];
ak = zeros(size(bk));

[x,t] = fourier(Ta,f0,Np,ak,bk);

figure(1)
plot(t,x, Color="b")
xlabel('t (s)')
ylabel('Sinal composto x(t)')
grid on

t1 = 0:0.001:2;

hold on
y = square(2*pi*1*t1);
plot(t1,y,Color='r')
grid on
