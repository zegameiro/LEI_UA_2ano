function matrix = makeMatrix(movies)
%makeMatrix Recebe os dados dos filmes, cria um array ordenado com todos os
% filmes e cria um array lógico MATRIX onde verifica se o user 1
% (movies(:,1)) assistiu ao filme 1 (set(1)). Se assistiu coloca 1, se não
% deixa a 0. No final obtemos um array do tipo
%   A forma deve ser 
%
% user1 user2 ... user_j  
% [ 1     0   ...   1  ;    -> movie_1
%   1     1   ...   0  ;    -> movie_2
%   0     0   ...   1  ;    -> movie_3
%  ...   ...  ...  ... ;    ...
%   0     1   ...   1   ]   -> movie_i
%       
%   USAGE -> matrix = makeMatrix(movies)

set = [];
for n = 1:length(movies)
    set = cat(1,set, movies);       % põe todos os filmes num array set
end

set = unique(set);                  % ordena o array e retira as repetições

matrix = false(length(set), length(movies));    % cria o array logico com tudo false(0) -> todos os filmes x todos os users
for movie_i = 1:size(matrix,1)
    for user_j = 1:size(matrix,2)
        matrix(user,movie) = any(movies{user_j}==set(movie_i));   % verifica se o user_j assistiu o movie_i 
    end
end

end