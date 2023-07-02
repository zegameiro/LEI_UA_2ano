%% Exercício 1 Guião 2
% Exercício 1 a)
periodo = 0.5;
t = 0:0.001:4 * periodo;
xt = 2 * sin(4 * pi * t);

figure(1)
plot(t,xt)
grid on
xlabel('t')
ylabel('x(t)')
title('x(t) = 2sen(4pi*t)')

% Exercício 1 b)

periodo = 0.2;
t = 0:0.001:4 * periodo;
yt = sin(10 * pi * t + pi / 2);

figure(2)
plot(t,yt)
grid on
xlabel('t')
ylabel('y(t)')
title('y(t) = sen(10pi*t + pi / 2)')

% Exercício 1 c)

periodo = 0.1;
t = 0:0.001:4*periodo;
pt = sin(20*pi*t + ((70 * pi) / 180)) + sin(20 * pi * t + ((200 * pi) / 180));

figure(3)
plot(t,pt)
grid on
xlabel('t')
ylabel('p(t)')
title('p(t) = sin(20*pi*t + ((70 * pi) / 180)) + sen(20 * pi * t + ((200 * pi) / 180))')

% Exercício 1 d)

periodo = 1;
t = 0:0.001:4*periodo;
zt = sin(6*pi*t) + sin(8 * pi * t);

figure(4)
plot(t,zt)
grid on
xlabel('t')
ylabel('z(t)')
title('z(t) = sin(6 * pi * t) + sen(8 * pi * t)')

% Exercício 1 e)

periodo = 2;
t = 0:0.001:4*periodo;
qt = sin(6*pi*t) + sin(7*pi*t)  + sin(8*pi*t);

figure(6)
plot(t,qt)
grid on
xlabel('t')
ylabel('w(t)')
title('q(t) = sin(6*pi*t) + sin(7*pi*t)  + sin(8*pi*t)')



