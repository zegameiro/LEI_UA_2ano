%% Código 1 - primeira versão

experiencias = rand(15,10000);
lancamentos = experiencias > 0.5; 
resultados = sum(lancamentos);
sucessos = resultados >= 6;
probSimulacao5 = sum(sucessos)/10000

% ------------------------------------------------------------------------
% ------------------------------------------------------------------------

%% Código 1 - segunda versão

N= 1e5; %número de experiências
p = 0.5; %probabilidade de cara
k = 6; %número de caras
n = 15; %número de lançamentos
lancamentos = rand(n,N) > p;
sucessos= sum(lancamentos) >= k;
probSimulacao6 = sum(sucessos)/N