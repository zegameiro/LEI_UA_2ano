function X_atual = markov_estadoestacionario(T,x0, lim)
% T -> Matriz de transição de estados
% x0 -> Vetor inicial
% lim -> limiar para terminar o processo
% (máximo da diferença entre os vetores em duas iterações 
%  deverá ser inferior a esse valor)

    N = length(T);
    
    if nargin == 1 % Se o número de argumentos da função for 1
        lim = 1e-5;
        x0 = ones(N,1) / N; % Cria um array de várias linhas e uma coluna de 1's
    elseif nargin == 2 % Se o número de argumentos for 2
        lim = 1e-5;
    end

    X_ant = x0; % Define X_ant como o vetor do estado inicial
    X_atual = T * X_ant; % Define X_atual para o vetor do próximo estado

    count = 1;

    while max(abs(X_ant - X_atual)) >= lim
        X_ant = X_atual;
        X_atual = T * X_ant;
        count = count + 1;
    end
    
end