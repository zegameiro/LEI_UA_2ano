%% Exercício 1 a) Guião 2 (2.1)
N = 1e6;  % Número de Experiências
p = 0.5;  % Probabilidade de ser rapaz
k = 1;    % Número de filhos(Masculino) esperados no acontecimento
n = 2;    % Número total de filhos

experiencias = rand(n,N) > p;
sucessos = sum(experiencias) >= k;
probSim_1a = sum(sucessos) / N

%% Exercício 1 c) Guião 2 (2.1)

p1 = sum(sum(experiencias) == 2);  % Probabilidade de os dois acontecimentos acontecerão ao mesmo tempo
p2 = sum(sum(experiencias) >= 1);  % Probabilidade do acontecimento que já aconteceu

probSim_1c = p1 / p2 % Fórmula da probabilidade condicionada P(A|B) = P(A&&B)/P(B)

%% Exercício 1 d) Guião 2 (2.1)

p1 = sum(sum(experiencias) == 2);
p2 = sum(experiencias(1,:));
probSim_1d = p1 / p2

%% Exercício 1 e) Guião 2 (2.1)

n = 5; % Número total de filhos

experiencias = rand(n,N) > p;
p2 = sum(sum(experiencias) >= 1); % Pelo menos 1 filho
p1 = sum(sum(experiencias) == 2); 
probSim_1e = p1 / p2

%% Exercício 1 f) Guião 2 (2.1)

p1 = sum(sum(experiencias)>=2);
p2 = sum(sum(experiencias)>=1);
probSim_1f = p1 / p2














