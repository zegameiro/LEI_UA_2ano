%% Exercício 2a) Miniteste 2017/18

clear
clc

fprintf("2a)-----------------------------------------\n")
%       C     D     E     F     G
H = [   0 , 1/2 , 1/3 , 1/4 ,   0 ;   % C
      1/2 ,   0 ,   0 , 1/4 , 1/2 ;   % D
      1/2 , 1/2 , 1/3 , 1/4 ,   0 ;   % E
        0 ,   0 ,   0 ,   0 , 1/2 ;   % F
        0 ,   0 , 1/3 , 1/4 ,   0 ];  % G

beta = 0.8;
N = 5; % Número de páginas
num_lig = sum(H);


A = beta * H + (1 - beta) * ones(N)/N;

disp(A)

%% Exercício 2b) Miniteste 2017/18
fprintf("2b)-----------------------------------------\n")

pagerank = ones(N,1)/N;
iter = 10;

for i = 1:iter
    pagerank = A * pagerank;
end

fprintf("Pagerank C: %.4f\n",pagerank(1))
fprintf("Pagerank D: %.4f\n",pagerank(2))
fprintf("Pagerank E: %.4f\n",pagerank(3))
fprintf("Pagerank F: %.4f\n",pagerank(4))
fprintf("Pagerank G: %.4f\n",pagerank(5))


