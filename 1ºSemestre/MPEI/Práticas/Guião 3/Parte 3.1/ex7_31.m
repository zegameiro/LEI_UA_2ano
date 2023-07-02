%% Exercício 7 a) Guião 3 (3.1)

disp("a) -------------------")

      %A  %B  %C  %D  %E  %F  
H =  [0 , 0 , 0 , 0 , 1/3 , 0 ;
      1 , 0 , 0 , 0 , 1/3 , 0 ; 
      0 , 1/2 , 0 , 1 , 0 , 0 ;
      0 , 0 , 1 , 0 , 0 , 0 ;
      0 , 1/2 , 0 , 0 , 0 , 0 ;
      0 , 0 , 0 , 0 , 1/3 , 0 ;];

pages = 6;  % Número de páginas
num_lig = sum(H);  % Número de ligações
N = 10; % Número de iterações
pagerank = ones(pages,1) / pages;

for i = 1:10
    pagerank = H * pagerank;
end

page_maxrank = find(pagerank == max(pagerank));

fprintf("\nA página com maior page rank é a %d \n",page_maxrank)

%% Exercício 7 b) Guião 3 (3.1)

disp("b) -------------------")

fprintf("\nNeste conjunto de páginas, a página F é um dead-end. Os ciclos B-E, E-B e C-D e D-C  são spider traps\n")

%% Exercício 7 d) Guião 3 (3.1)

disp("d) -------------------")

p=0.8;
A=p*H+(1-p)*ones(N)/N; % matriz da Google
A(isnan(A))=1/N % resolver dead ends
