%% 29/09/2022 Guião 1
% Exercício 1 a)

t = 0:0.1:5;

xt = 2 .* sin(4 * pi * t);

figure(1)
plot(t,xt,LineWidth=2,Color='r')

% Exercício 1 b)

yt = cos(10 * pi * t);

figure(2)
plot(t,yt,LineWidth=2,Color='g')

% Exercício 1 c)

zt = xt .* yt;

figure(3)
plot(t,zt,LineWidth=2,Color="k")

% Exercício 1 d)

t1 = 0:0.01:10;

wt1 = 3 .* sin(pi * t1) + 2 * sin(6 * pi * t1);

figure(4)
plot(t1,wt1,LineWidth=2,Color='b')

% Exercício 1 e)

t2 = t;
t3 = t;

[TT2,TT3] = meshgrid(t2,t3);

qt23 = 2 .* sin(2 * pi * ((2 .* TT2) + TT3));

figure(5)
surf(TT2,TT3,qt23)
shading interp








