%% Exercício 7 a) Guião 2 (2.2)

clear
clc

n = 1;
k = 0;
p = 15 * n;
lambda = n * p;

prob_7a = ((lambda ^ k) ./ factorial(k)) .* exp(-lambda);
fprintf("Usando a Distribuição de Poisson = %e \n",prob_7a)

%% Exercício 7 b) Guião 2 (2.2)

prob1 = 0;

for k = 0:10
    prob1 = prob1 + ((lambda ^ k) ./ factorial(k)) .* exp(-lambda);  %Calcular a probabilidade de receber exatamente 10 mensagens
end

prob_7b = 1 - prob1;

fprintf("Usando a Distribuição de Poisson = %e \n",prob_7b)
