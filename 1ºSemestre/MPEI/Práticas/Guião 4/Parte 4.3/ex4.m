%% Exercício 4 Guião 4 (4.3)

%% Processar dados

numHash = 200;   % Nº funções de dispersão
[users,Set] = criarSet("u.data");

assinaturas = minHash(Set,numHash);

threshold = 0.4;
similares = obterSimilaresHash(users,assinaturas,threshold,numHash);

% Mostrar Resultados
N = length(similares);
for k= 1:N
  fprintf("Distância: %f  (user 1: %d , user 2: %d)\n",similares(k,3),similares(k,1),similares(k,2));
end