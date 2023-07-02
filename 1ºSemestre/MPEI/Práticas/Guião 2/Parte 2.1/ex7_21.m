%% Exercício 7 a) Guião 2 (2.1)
clc
clear

probA = 0.01;   % Probabilidade do erro ser do André
probB = 0.05;   % Probabilidade do erro ser do Bruno
probC = 0.001;  % Probabilidade do erro ser do Carlos

nA = 20;  % Programas do André
nB = 30;  % Programas do Bruno
nC = 50;  % Programas do Carlos
ntotal = 100;  % Número total de programas

N = 1e6;

% Array de 0's e 1's se os programas do André/Bruno/Carlos têm erros ou não

prgA = rand(nA,N) < probA;
prgB = rand(nB,N) < probB;
prgC = rand(nC,N) < probC;

prg = [prgA ; prgB ; prgC];

% Casos possíveis (com erro)

cp_erro = sum(sum(prg));

% Casos favoráveis Carlos (com erro)

cf_erroC = sum(sum(prgC));

% Probabilidade do programa ser do Carlos

prob_prgC = cf_erroC / cp_erro

%% Exercício 7 b) Guião 2

% Casos favoráveis André (com erro)

cf_erroA = sum(sum(prgA));

% Probabilidade do programa ser do André

prob_prgA = cf_erroA / cp_erro;

% Casos favoráveis Bruno (com erro)

cf_erroB = sum(sum(prgB));

% Probabilidade do programa com erro ser do Bruno

prob_prgB = cf_erroB / cp_erro;

if prob_prgA > prob_prgB && prob_prgA > prob_prgC
   strA = 'O programa com erro é mais provável de ser do André';
   disp(strA)
elseif prob_prgB > prob_prgA && prob_prgB > prob_prgC
   strB = 'O programa com erro é mais provável de ser do Bruno';
   disp(strB)
else
   strC = 'O programa com erro é mais provável de ser do Carlos';
   disp(strC)
end