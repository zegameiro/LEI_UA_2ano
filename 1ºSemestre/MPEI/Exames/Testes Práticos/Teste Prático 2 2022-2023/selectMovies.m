function movie_list = selectMovies(sign,data,rated_movies, threshold)
% Esta função tem como objetivo selecionar filmes, tendo por base a
% distância de Jaccard entre e as avaliações de cada utilizador

% sign -> matriz de assinaturas de cada filme
% rated_movies -> array com os filmes visualizados e avaliados pelo utilizador selecionado
% data -> ficheiro com as informações sobre os filmes

    n_movies = size(sign,1); % Número de filmes

    movie_list = {};

    for movie1 = 1:n_movies
        for movie2 = movie1 + 1:n_movies
            if ~ismember(movie1,rated_movies) && ~ismember(movie2,rated_movies) % verifica se os filmes não foram avaliados pelo utilizador inserido
                dist = sum(sign(movie1,:) == sign(movie2,:)) / size(sign,2);
                if dist < threshold
                    movie_list{end + 1} = data{movie1,1};
                    movie_list{end + 1} = data{movie2,1};
                end
            end
        end
    end
    
end