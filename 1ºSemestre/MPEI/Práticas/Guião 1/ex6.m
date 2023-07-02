%% Método Analítico

p = 0.3; % probabilidade de sair defeituosa
k = 3;   % Número de peças defeituosass
n = 5;   % Repetições da experiência aleatória (númer)
prob = nchoosek(n,k) * p^k * (1-p)^(n-k)  % nchoosek(n,k)= n!/(n-k)!/k!

%% Método por Simulação

N = 1e6;

lancamentos = rand(n,N) < p;
sucessos = sum(lancamentos) == k;
probabilidade = sum(sucessos)/N

