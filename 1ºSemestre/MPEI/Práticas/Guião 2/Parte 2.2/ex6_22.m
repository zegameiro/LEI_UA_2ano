%% Exercício 6 a) Guião 2 (2.2)

clear
clc

n = 8000;      % Número de peças
k = 7;         % Número de peças defeituosas
p = 1 / 1000;  % Probabilidade de uma peça ser defeituosa

prob_6a = nchoosek(n,k) * p^k * (1-p)^(n-k);

fprintf("Usando a Distribuição Binomial = %e \n", prob_6a);

%% Exercício 6 b) Guião 2 (2.2)

lambda = n * p;

prob_6b = ((lambda ^ k) ./ factorial(k)) .* exp(-lambda);

fprintf("Usando a Distribuição de Poisson = %e /n",prob_6b)






