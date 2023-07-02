%% Exercício 6 a) Guião 2 (2.1)

clc
clear

% Acontecimento A –> a soma dos dois valores é igual a 9

N = 1e6;    % Número de experiencias
n = 2;      % Número de lançamentos
m = 6;      % Valor Máximo

array = randi(m, n, N);

diferentes= zeros(1,N);
casos_favA = 0;

for col=1:N
    if sum(array(:,col)) == 9
     casos_favA = casos_favA +1;
    end
end

probA = sum(casos_favA)/N

%% Exercício 6 b) Guião 2 (2.1)

% Acontecimento B –> o segundo valor é par

casos_favB = 0;

for col = 1:N
    if (rem(array(2,col),2))==0 % ver se o numero é par
        casos_favB= casos_favB+1;
    end
end

probB = sum(casos_favB)/N

%% Exercício 6 c) Guião 2 (2.1)

% Acontecimento C –> pelo menos um dos valores é igual a 5

casos_favC = 0;

for col = 1:N
    if array(1,col) == 5 || array(2,col) == 5
        casos_favC = casos_favC + 1;
    end
end

probC = sum(casos_favC) / N

%% Exercício 6 d) Guião 2 (2.1)

% Acontecimento D –> nenhum dos valores é igual a 1

casos_favD = 0;

for col=1:N
    if array(1,col) ~= 1 && array(2,col) ~= 1
        casos_favD = casos_favD + 1;
    end
end

probD = sum(casos_favD) / N