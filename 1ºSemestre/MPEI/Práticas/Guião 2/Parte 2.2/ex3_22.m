%% Exercício 3 a) Guião 2 (2.2)
clear
clc

N = 1e6;    % Número de experiências
n = 4;      % Número de lançamentos

lancamentos = rand(n,N) > 0.5;
prob0 = sum(sum(lancamentos) == 0) / N;
prob1 = sum(sum(lancamentos) == 1) / N;
prob2 = sum(sum(lancamentos) == 2) / N;
prob3 = sum(sum(lancamentos) == 3) / N;
prob4 = sum(sum(lancamentos) == 4) / N;

%% Exercício 3 b) Guião 2 (2.2)

X = [0 1 2 3 4];
pX =  [prob0 prob1 prob2 prob3 prob4];
stem(X,pX);
media = sum(X .* pX)

aux = (X - media).^2;
variancia = sum(aux .* pX)

dp = sqrt(variancia)


%% Exercício 3 c) Guião 2 (2.2)
p = 0.5;
k = 4;
n = 4;

for x = 0:k
    prob(x+1) = nchoosek(n,x) * p^x * (1-p)^(n-x);  % nchoosek(n,k)= n!/(n-k)!/k!
end

prob

%% Exercício 3 e) Guião 2 (2.2)

Ex = sum(X .*prob)

Varx = sum(((X -Ex).^2) .* prob)

%% Exercício 3 f) i. Guião 2 (2.2)

pfi = (1 - prob(1)) - prob(2)

%% Exercício 3 f) ii. Guião 2 (2.2)

pfii = ((1 - prob(3)) - prob(4)) - prob(5)

%% Exercício 3 f) iii. Guião 2 (2.2)

pfiii = (1 - prob(1)) - prob(5)
