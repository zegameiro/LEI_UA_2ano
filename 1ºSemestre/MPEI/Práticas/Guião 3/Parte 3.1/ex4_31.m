%% Exercício 4a) Guião 3 (3.1)

clc
clear

disp("a) -------------------")

p = 0.4;
q = 0.6;
     % A      B      C      D
mT = [p^2 (1-p)^2 p*(1-p) p*(1-p);   % A
       0      0      0      1;       % B
       0      0      0      1;       % C
      q^2  q*(1-q) q*(1-q) (1-q)^2]' % D

%% Exercício 4b) Guião 3 (3.1)

disp("b) -------------------")

x0 = [1,0,0,0]';

values = [5 10 100 200];

for i= 1:4
    prob_4b = mT^values(i) * x0;
    fprintf("  \n A probabilidade de estar em cada estado ao fim de %d transições é :\n A = %.8f,\n B = %.8f,\n C = %.8f,\n D = %.8f\n",values(i),prob_4b)
end

%% Exercício 4c) Guião 3 (3.1)

disp("c) -------------------")

n = length(mT);
M = [mT-eye(n) ; ones(1,n)]; % A função eye gera uma matriz identidade de tamanho n
x = [zeros(n,1);1];
v = M\x
