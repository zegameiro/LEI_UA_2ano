%% Exercício 4 Guião 2
f1 = 10;
f2 = 20;
f3 = 30;
fase = rand(1,3)*2*pi - pi;
fase1 = fase(1,1);
fase2 = fase(1,2);
fase3 = fase(1,3);
t = 0:0.001:0.;

xt = sin(2*pi*f1*t + fase1) + sin(2*pi*f2*t + fase2) + sin(2*pi*f3*t + fase3);


plot(t,xt)
grid on
title('x(t)')