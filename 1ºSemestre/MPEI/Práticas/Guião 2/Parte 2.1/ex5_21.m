%% Exercício 5 a) Guião 2 (2.1)

N = 1e6;    % Número de experiências
n = 6;      % Número de faces 
aux = 0;
lancamentos = randi(n,2,N);

% Acontecimento A - a soma dos valores é igual a 9
% Acontecimento B - o segundo valor é par
% Acontecimento C - pelo menos um dos valores é igual a 5
% Acontecimento D - nenhum dos valores é igual a 1

%% Acontecimento A

for i = 1:N
    if lancamentos(1,i) + lancamentos(2,i) == 9
        aux = aux + 1;
    end
end

probSim_5A = aux / N

%% Acontecimento B

lancamentos = randi(n,2,N);
aux = 0;
for i = 1:N
    if mod(lancamentos(2,i),2) == 0
        aux = aux + 1;
    end
end

probSim_5B = aux / N

%% Acontecimento C

lancamentos = randi(n,2,N);
aux = 0;
for i = 1:N
    if lancamentos(1,i) == 5 || lancamentos(2,i) == 5
        aux = aux + 1;
    end
end

probSim_5C = aux / N

%% Acontecimento D

lancamentos = randi(n,2,N);
aux = 0;
for i = 1:N
    if lancamentos(1,i) ~= 1 && lancamentos(2,i) ~= 1
        aux = aux + 1;
    end
end

probSim_5D = aux / N