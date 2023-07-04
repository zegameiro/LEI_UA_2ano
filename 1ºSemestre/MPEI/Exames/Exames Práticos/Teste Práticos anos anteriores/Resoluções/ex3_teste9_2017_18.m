%% Exercício 3a) Miniteste 2017/18

clear
clc

fprintf("3a)-------------------------------------------------------------\n")

%       a     b     c     d   ?   .
T = [ 0.7 , 0.2 ,   0 ,   0 , 0 , 0 ;  % a
      0.2 ,   0 , 0.3 ,   0 , 0 , 0 ;  % b
        0 , 0.6 , 0.3 ,   0 , 0 , 0 ;  % c
      0.1 , 0.2 , 0.3 , 0.1 , 0 , 0 ;  % d
        0 ,   0 , 0.1 , 0.5 , 1 , 0 ;  % ? 
        0 ,   0 ,   0 , 0.4 , 0 , 1 ] % . 

%% Exercício 3b) Miniteste 2017/18

fprintf("3b)-------------------------------------------------------------\n")

v0 = [ 1 ; 
       0 ; 
       0 ; 
       0 ;
       0 ;
       0 ];

prob_10_c = T^9 * v0;
prob_15_d = T^14 * v0;

fprintf("Prob. décimo carácter ser 'c' = %.4f\n",prob_10_c(3))
fprintf("Prob. décimo quinto carácter ser 'd' = %.4f\n",prob_15_d(4))

%% Exercício 3c) Miniteste 2017/18

fprintf("3c)-------------------------------------------------------------\n")

% Para calcular a média, é preciso a matriz fundamental, obtida antes
% através da matriz Q, que é a matriz dos estados trasientes.
% Como neste caso a matriz já vem na forma canonica (tivemos sorte),
% podemos obter Q através de 
Q = T(1:4,1:4);

% Cálculo da matriz fundamental
F= (eye(size(Q)) - Q)^-1;
% temos de somar sempre a matriz
E= sum(F);
fprintf("O número médio do comprimento das cadeias de carateres começando no estado c é %.0f\n",E(3))

