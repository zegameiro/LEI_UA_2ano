%% 29/09/2022 Guião 1
% Exercício 2
% Gráfico do ex 1a)
t = 0:0.1:5;

xt = 2 .* sin(4 * pi * t);
subplot(2,3,1)
plot(t,xt,LineWidth=1,Color='r')

% Gráfico do ex 1b)
yt = cos(10 * pi * t);

subplot(2,3,2)
plot(t,yt,LineWidth=2,Color='b')

% Gráfico do ex 1c)
zt = xt .* yt;

subplot(2,3,3)
plot(t,zt,LineWidth=2,Color="g")

% Gráfico do ex 1d)
t1 = 0:0.01:10;

wt1 = 3 .* sin(pi * t1) + 2 * sin(6 * pi * t1);

subplot(2,3,4)
plot(t1,wt1,LineWidth=2,Color='k')

% Gráfico do ex 1e)

t2 = t;
t3 = t;

[TT2,TT3] = meshgrid(t2,t3);

qt23 = 2 .* sin(2 * pi * ((2 .* TT2) + TT3));

subplot(2,3,5)
surf(TT2,TT3,qt23)
shading interp