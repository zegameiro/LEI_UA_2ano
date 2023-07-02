%% Exercício 1a) Guião 3 (3.2)

clear
clc

fprintf("\n1a)------------------------------ \n")

    % 1     2     3    4   5
H = [0.8 , 0   , 0 , 0.3 , 0 ;         % 1
     0.2 , 0.6 , 0 , 0.2 , 0 ;         % 2
     0   , 0.3 , 1 , 0   , 0 ;         % 3
     0   , 0.1 , 0 , 0.4 , 0 ;         % 4
     0   , 0   , 0 , 0.1 , 1];         % 5

N = 1e4; % Número de experiências (Se for demasiado elevado o tempo de execução aumenta)
N_x1 = zeros(N,1);  % Vetor para guardar o número de passos até à absorção com x0 = 1
N_x2 = zeros(N,1);  % Vetor para guardar o número de passos até à absorção com x0 = 2
N_x4 = zeros(N,1);  % Vetor para guardar o número de passos até à absorção com x0 = 4

E1_absor3 = 0; % Número de absorções no estado 3, com x0 = 1
E1_absor5 = 0; % Número de absorções no estado 5, com x0 = 1

for i  = 1:N
    aux = crawl(H,1,[3 , 5]); % Começa no estdo 1 e acaba no estado 3 ou 5, pois são os estados absorventes

    if aux(1,end) == 3  % end -> último valor
        E1_absor3 = E1_absor3 + 1;
    else
        E1_absor5 = E1_absor5 + 1;
    end

    N_x1(i) = length(aux) - 1;
    N_x2(i) = length(crawl(H,2,[3 , 5])) - 1;
    N_x4(i) = length(crawl(H,4,[3 , 5])) - 1;
end

media_x1 = sum(N_x1) / N;
media_x2 = sum(N_x2) / N;
media_x4 = sum(N_x4) / N;

probSim_absor3 = E1_absor3 / N;
probSim_absor5 = E1_absor5 / N;

fprintf("A média do número de passos até à absorção é:\n  Começando no estado 1 = %.4f \n",media_x1)
fprintf("\n  Começando no estado 2 = %.4f \n",media_x2)
fprintf("\n  Começando no estado 4 = %.4f \n \n",media_x4)

fprintf("A probabilidade de absorção começando no estado 1 é:\n  Do estado 3 = %.4f \n",probSim_absor3)
fprintf("\n  Do estado 5 = %.4f \n",probSim_absor5)

%% Exercício 1b) Guião 3 (3.2)

fprintf("\n1b)------------------------------ \n")

N1 = 10000;    % Tamanho do cell array
estados = [1,2,4]; % Estados não absorvente
E = datasample(estados,N1);  % Estados escolhidos aleatoriamente

a = cell(1,N1);

for i = 1:N1
    a{i} = crawl(H,1,[3 , 5]);
end

size_seq = cellfun('length',a);  % Cellfun aplica a função no primeiro argumento aos cells do cell array
max_seq = max(size_seq);
min_seq = min(size_seq);

fprintf("Comprimento máximo das sequências geradas = %d \n",max_seq)
fprintf("Comprimento mínimo das sequências geradas = %d \n", min_seq)
