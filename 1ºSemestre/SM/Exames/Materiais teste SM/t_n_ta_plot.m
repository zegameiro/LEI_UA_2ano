Ta = 0.01;
Np = 3;
T = 0.5;
f = 1 / T;
Nt = (T / Ta) * Np;
t = (0:Nt-1) * Ta;
x = sin(2 * f * pi * t);
plot(t,x)