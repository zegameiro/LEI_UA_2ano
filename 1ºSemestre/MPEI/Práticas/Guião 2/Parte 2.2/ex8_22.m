%% Exercício 8 Guião 2 (2.2)

clear
clc

n = 100; % Número de páginas
lambda = 0.02 * n;
prob_8 = 0;

for k = 0:1
    prob_8 = prob_8 + ((lambda ^ k) ./ factorial(k)) .* exp(-lambda);
end

fprintf("Usando a Distribuição de Poisson = %e \n",prob_8)


