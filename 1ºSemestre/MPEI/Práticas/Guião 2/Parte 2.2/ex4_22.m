%% Exercício 4a) i. Guião 2 (2.2)

clear
clc

N = 1e6;    % Número de experiências
n = 5;      % Número de peças
lancamentos = rand(n,N) < 0.3;

for i = 0:n
    probSim_4(i+1) = sum(sum(lancamentos) == i) / N;
end

X =0:1:5;

probSim_4

%% Exercício 4a) ii. Guião 2 (2.2)

fX = cumsum(probSim_4);

figure(1)
stairs([0 X 6],[0 fX 1])
xlabel('Peças Defeituosas')
ylabel('Probabilidade')
title('Função de distribuição acumulada')
grid on

%% Exercício 4a) iii. Guião (2.2)

proba_iii = 1 - probSim_4(4) - probSim_4(5) - probSim_4(6)
