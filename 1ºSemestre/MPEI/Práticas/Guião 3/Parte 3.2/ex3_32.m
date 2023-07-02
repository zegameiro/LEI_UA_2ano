%% Exercício 3 a) Guião 3 (3.2)

clear
clc

fprintf("\n 3a)------------------------------ \n")

n_estados = 6; % Número de estados

%Na matriz os estados têm de aparecer por ordem alfabética

%     a.pt   b.es   c.fr    d.br   e.com   f.nl 
H = [  0  ,  0.5  ,  0.5  ,  0  ,  0.25  ,  0  ;     % a.pt
       1  ,    0  ,    0  ,  0  ,  0.25  ,  0  ;     % b.es
       0  ,    0  ,    0  ,  1  ,  0.25  ,  0  ;     % c.fr
       0  ,    0  ,  0.5  ,  0  ,  0.25  ,  1  ;     % d.br
       0  ,    0  ,    0  ,  0  ,     0  ,  0  ;     % e.com
       0  ,  0.5  ,    0  ,  0  ,     0  ,  0  ]     % f.nl

beta = 0.85;
saidas = sum(H);
H = H./saidas;
H(isnan(H)) = 0;
A_google = beta * H + (1 - beta) * ones(n_estados) / n_estados;

page_rank = ones(n_estados,1) / n_estados;
new_page_rank = A_google * page_rank;

while(sum(abs(new_page_rank - page_rank)) > 0.0001)  % Diferença de pagerank entre duas iterações não exceda 0.01 em valor absoluto para todas as páginas
    page_rank = new_page_rank;
    new_page_rank = A_google * new_page_rank;
end

disp(new_page_rank)
fprintf("A melhor página é a d.br e a pior é a e.com")

%% Exercício 3 b) Guião 3 (3.2)

fprintf("\n 3b)------------------------------ \n")

M = [A_google - eye(n_estados); ones(1,n_estados)];
x = [0 ; 0 ; 0 ; 0 ; 0 ; 0 ; 1];
u = M \ x;

fprintf("\nProbabilidades limite dos estados:\n" + ...
    "\ta.pt (%.4f), b.es (%.4f), c.fr (%.4f), d.br (%.4f), e.com (%.4f) e f.nl (%.4f)\n",u(1),u(2),u(3),u(4),u(5),u(6));


