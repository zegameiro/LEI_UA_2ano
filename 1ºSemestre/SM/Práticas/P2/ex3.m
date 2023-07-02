%% 29/09/2022 Guião 1
% Exercício 3

% Gráfico do ex 1a) (x(t))
t = 0:0.1:5;

xt = 2 .* sin(4 * pi * t);
plot(t,xt,LineWidth=1,Color='r',LineStyle='-')
hold on

% Gráfico do ex 1b) (y(t))

yt = cos(10 * pi * t);

plot(t,yt,LineWidth=2,Color='b',LineStyle='--')

% Gráfico do ex 1c) (z(t))

zt = xt .* yt;

plot(t,zt,LineWidth=1,Color="g",LineStyle=":")

% Gráfico do ex 1d) (w(t))

t1 = 0:0.1:10;

wt1 = 3 .* sin(pi * t1) + 2 .* sin(6 * pi * t1);

plot(t1,wt1,LineWidth=2,Color='y',LineStyle='-')
legend('x(t)','y(t)','z(t)','w(t)')
hold off



