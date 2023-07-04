function sign_styles = minHash_movieStyles(data,numHash)
    n_movies = size(data,1);

    sign_styles = Inf(n_movies,numHash);

    wb = waitbar(0,'Getting MinHash of styles...');

    for movie = 1:n_movies
        waitbar(movie/n_movies,wb);
        for style_index = 2:7  % Porque os estilos dos filmes situam-se nas posições 2 a 7
            style = data{movie,style_index};
            if ~isempty(style) % Verifica se o estilo existe
                hashCodes = DJB31MA_mod(style,127,numHash);
                sign_styles(movie,:) = min(hashCodes,sign_styles(movie,:));
            end
        end
    end

    delete (wb)

end