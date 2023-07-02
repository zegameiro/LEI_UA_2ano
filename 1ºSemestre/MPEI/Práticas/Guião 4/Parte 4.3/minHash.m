function sign = minHash(matrix, k)
%minHash        Recebe um array lógico MATRIX (formatado com a função makeMatrix())
% e usa uma função de minHash K vezes para calcular as asssinaturas.
%   USAGE -> sign = minHash(matrix, k)
%
%   Consultar help makeMatrix para entender as dimensões da MATRIX passada 
%   como argumento
%
%   Usa permutações com base em números primos para calcular os indices.
%   A permutação N calcula uma ronda de indices aleatórios.
%   Após isto, é verificado, para cada user, o menor indice para o qual o
%   user viu um movie (matrix(indice) == 1). Esse indice é registado no array SIGN
%   na linha N, na coluna do user_j.
%
%   Este processo repete-se K vezes.
%       
%   De um array MATRIX obtemos um array SIGN na primeira permutação PERM:
%   
%   PERM     ->         MATRIX         ->           SIGN
%   index       user1   user2   user3       user1   user2   user3
%   [ 5;       [  1       0      1  ;      [  1       2       4  ]
% ->  2;          1      (1)     0  ;
%     3;          0       1      0  ;
% ->  1;         (1)      0      0  ;
% ->  4 ]         1       1     (1)  ]



end