%% Exercício 3 a) Guião 2 (2.1)

N = 1e6;  % Número de experiências
T = 1000; % Dimensão do array
k = 10;   % Número de Keys
aux = 0;

for i = 1:N
    exper = randi(T,1,k);  % Cria um array de tamanho 1x10 com números inteiros aleatórios de 1 a 1000
    if length(exper) ~= length(unique(exper))
        aux = aux + 1;
    end
end

probSim_3a = aux / N

%% Exercício 3 b) Guião 2 (2.1)

k = 10:10:100; % Número de keys
T = 1000;      % Tamanho do Array
N = 1e6;       % Númeor de experiências

for i = 1:length(k)
    exper = randi(T,k(i),N);
    count = 0;
    for j = 1:N
        if length(exper(:,j)) ~= length(unique(exper(:,j)))
            count = count + 1;
        end
    end
    probSim_3b(i) = count / N;
end

figure(1)
plot(k,probSim_3b)
xlabel("Keys")
ylabel("Probabilidade")

%% Exercício 3 c) Guião 2 (2.1)

k = 50;             % Número de keys
T = 100:100:1000;   % Tamanho do Array
N = 1e6;            % Número de experiências

for i = 1;length(T)
    exper = randi(k,T(i),N);
    aux = 0;
    for x = 1:N
        if length(unique(exper(:,x))) == i
            aux = aux + 1;
        end
    end
    probSim_3c(i) = aux / N; 
end

figure(2)
plot(T,probSim_3c)
ylabel('Probabilidade')
xlabel('Tamanho do Array')