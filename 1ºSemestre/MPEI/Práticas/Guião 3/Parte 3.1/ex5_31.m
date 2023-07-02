%% Exercício 5a) Guião 3 (3.1)
clear
clc

disp("a) -------------------")

T = [0.7 0.2 0.1;
     0.2 0.3 0.5;
     0.3 0.3 0.4]'

sum(T);

sol = 1;
nuvens = 2;
chuva = 3;

%% Exercício 5b) Guião 3 (3.1)

disp("b) -------------------")

x0 = [1 0 0]';  % Estado 1 dia com sol
x1 = T * x0;    % Estado 2 dias com sol
x2 = T * x0;    % Estado 3 dias com sol

prob_5b = 1 * x1(1) * x2(1)

% OU

prob_5b1 = 1 * T(sol,sol) * T(sol,sol)

%% Exercício 5c) Guião 3 (3.1)

disp("c) -------------------")

prob_5c = T(sol,sol) * T(sol,sol) + T(nuvens,sol) * T(sol,nuvens) + T(nuvens,sol) * T(nuvens,nuvens) + T(sol,sol) * T(nuvens,sol)

%% Exercício 5d) Guião (3.1)

disp("d) -------------------")

u = [1;0;0];
dias5d = u;

for i = 1:30
    u = T * u;
    dias_5d = dias5d + u;
end

fprintf("\nSabendo que o primeiro dia é sol\n")
fprintf("O número médio de dias de sol é %.4f \n",dias5d(sol))
fprintf("O número médio de dias de chuva é %.4f \n",dias5d(nuvens))
fprintf("O número médio de dias de nuvens é %.4f \n",dias5d(chuva))

%% Exercício 5e) Guião (3.1)

disp("e) -------------------")

u = [0;0;1];
dias_5e = u;

for i = 1:30
    u = T * u;
    dias_5e = dias_5e + u;
end

fprintf("\nSabendo que o primeiro dia é chuva\n")
fprintf("O número médio de dias de sol é %.4f \n",dias_5e(sol))
fprintf("O número médio de dias de nuvens é %.4f \n",dias_5e(nuvens))
fprintf("O número médio de dias de chuva é %.4f \n",dias_5e(chuva))

%% Exercício 5f) Guião (3.1)

disp("f) -------------------")

u = [1;0;0];
dias_5f = 0.1 * u(sol) + 0.3 * u(nuvens) + 0.5 * u(chuva);

for i = 1:30
    u = T * u;
    dias_5f = dias_5f + 0.1 * u(sol) + 0.3 * u(nuvens) + 0.5 * u(chuva);
end

fprintf("\nSabendo que o primeiro dia é sol\n")
fprintf("O número médio de dias com dores é %.4f \n",dias_5f)

u = [0;0;1];
dias_5f = 0.1 * u(sol) + 0.3 * u(nuvens) + 0.5 * u(chuva);

for i = 1:30
    u = T * u;
    dias_5f = dias_5f + 0.1 * u(sol) + 0.3 * u(nuvens) + 0.5 * u(chuva);
end

fprintf("\nSabendo que o primeiro dia é chuva\n")
fprintf("O número médio de dias com dores é %.4f \n",dias_5f)








