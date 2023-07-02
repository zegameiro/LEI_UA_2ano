%% Exercício 2 a) Guião 2 (2.1)

n = 20;    % Número de dardos
m = 100;   % Número de alvos
count = 0;
N = 1e6;   % Número de experiências

experiencias = randi([1 m],n,N);

for i = 1:N
    if length(unique(experiencias(:,i))) == n
        count = count + 1;
    end
end

probSim_2a = count / N;

%% Exercício 2 b) Guião 2 (2.1)

count = 0;
for i = 1:N
    if length(unique(experiencias(:,i))) < n
        count = count + 1;
    end
end

probSim_2b = count / N;

%% Exercício 2 c) Guião 2 (2.1)

N = 1e5;
n = 10:10:100; % Número de dardos
m = 1000; % Número de alvos

for j =1:length(n)
    sucessos = 0;
    lancamentos = randi(m,n(j),N);
    for i = 1: N
        if length(unique(lancamentos(:, i))) <= n(j) - 1
            sucessos = sucessos +1;
        end
    end
    prob1(j) = sucessos/N;    
end 


n2 = 10:10:100; % Número de dardos
m = 10000; % Número de alvos

for j= 1: length(n2)
        sucessos2 = 0;
        lancamentos = randi(m, n2(j), N);
        for i = 1: N
            if length(unique(lancamentos(:,i))) <= n(j) - 1
                sucessos2 = sucessos2 +1;
            end
        end
        prob2(j) = sucessos2/N;
end 

figure(1);
subplot(1,2,1)
plot(n, prob1);
subplot(1,2,2)
plot(n, prob2);

%% Exercício 2 d) Guião 2 (2.1)
clear;
clc;
N = 1e5;
m = [200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000]; % Número de alvos
n = 100; % Número de dardos

for k = 1: length(m)
    lancamentos = randi(m(k), n, N);
    sucessos3 = 0;
    for i = 1: N
        if length(unique(lancamentos(:, i))) <= n -1
            sucessos3 = sucessos3 +1;
        end 
    end
    prob3(k) = sucessos3/N;
end 

figure(2)
plot(m, prob3)


