%% Exercício 3 Guião 3
clear
clc

f0 = 1;
Ta = 0.001;
T0 = 1 / f0;
Np = 4;
bk = [0 4/pi 0 4/(3*pi) 0 4/(5*pi) 0 4/(7 * pi)];
ak = zeros(size(bk));
[x,t] = fourier(Ta,f0,Np,ak,bk);
disp(x)

[ak1,bk1] = fourier_coef(Ta,T0,x,8);
disp(ak1)
disp(bk1)

y = sawtooth(2 * pi * f0 *t + pi * pi/2,1/2);

plot(t,y)
grid on

