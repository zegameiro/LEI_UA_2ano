%% Exercício 1a) Teste Prático 2015/16

clear
clc

%       A     B     C     D     E     F
H = [   0 ,   0 , 1/2 ,   0 , 1/2 , 1/5 ;   % A
      1/2 , 1/3 , 1/2 , 1/2 , 1/2 , 1/5 ;   % B
        0 ,   0 ,   0 ,   0 ,   0 , 1/5 ;   % C
        0 , 1/3 ,   0 ,   0 ,   0 , 1/5 ;   % D
      1/2 ,   0 ,   0 , 1/2 ,   0 , 1/5 ;   % E
        0 , 1/3 ,   0 ,   0 ,   0 ,   0 ];  % F

n_pages = 6; % Número de páginas
n_links = sum(H); % Número de ligações
n_iter = 3; % Número de iterações

pagerank = ones(n_pages,1) / n_pages; % Criação do array pagerank

for i = 1:n_iter
    pagerank = H * pagerank;
end

fprintf("PageRank A = %.4f\nPagerank B = %.4f\nPageRank C = %.4f\nPagerank D = %.4f\nPageRank E = %.4f\nPageRank F = %.4f\n",pagerank)

%% Exercício 1b) Teste Prático 2015/16

pagerank1 = ones(n_pages,1) / n_pages; % Criação do array pagerank
pageranks = pagerank1;
n_iter = 8;

for i = 2:n_iter
    pagerank1 = H * pagerank;
    pageranks = [pageranks, pagerank1]; % Adicionar ao array pageranks os novos valores de pageRank  
end

iter = 1:n_iter;

figure(1)
plot(iter,pageranks)
xlabel("Nº iterações")
ylabel("Valor de PageRank")
title("Evolução do valor do pagerank")
legend("Page A","Page B","Page C","Page D","Page E","Page F")
grid on

%% Exercício 1c) Teste Prático 2015/16

max_pageRank = max(pagerank);
page_max = find(pagerank == max_pageRank);
fprintf("\nPágina com o maior valor de PageRank = %d (B)\nValor máximo do PageRank = %.4f",page_max,max_pageRank);

