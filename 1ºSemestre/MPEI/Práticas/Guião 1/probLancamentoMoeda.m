function [probabilidade] = probLancamentoMoeda(p,nc,N)
% p - Número de lançamentos
% nc - Número de caras
% N - Experiências a realizar
% pc - Probabilidade de sair cara
pc = 0.5;
lancamentos = rand(p,N) > pc;
sucessos = sum(lancamentos) == nc;
probabilidade = sum(sucessos)/N;
end
