%% Main 

clear
clc

readData % Ir buscar as variáveis que estao no ficheiro readData.m

insert_user = "Insert User ID (1 to 943): ";
userID = input(insert_user);

while  userID < 1 ||  userID > 943
    insert_user = "Please insert a valid user between 1 and 943: ";
    userID = input(insert_user);
end

options = "1 - Your movies \n" + ...
          "2 - Suggestion of movies based on other users \n" + ...
          "3 - Suggestion of movies based on already evaluated movies \n" + ...
          "4 - Movies feedback based on popularity \n" + ...
          "5 - Exit \n";

fprintf(options)
prompt = "Select choice: ";
choice = input(prompt);

while choice ~= 5
    switch (choice)
        case 1 
            
            fprintf("----------------------------------------------------------------\n")
            fprintf("Movies Watched:\n")
            n_user_movies = length(movies{userID,1});

            for i = 1:n_user_movies % Percorre os filmes visualizados pelo utilizador
                user_movies = movies{userID,1}; % Vai buscar a lista dos ID's dos filmes visualizados
                for movie = user_movies(i,1)
                    movie_name = data{movie,1}; % Vai buscar o nome do filme ao ficheiro fims.txt
                    fprintf("   %d -> %s \n",movie,movie_name);
                end
            end
 
        case 2
            
            fprintf("----------------------------------------------------------------\n")
            n_users = length(movies); % Número de utilizadores
            threshold = 0.4;
            numhash = 5;
            similar_users = getSimilaresHash(users,sign,threshold,numhash); % Encontra os utilizadores mais similares
            
            most2_similarusers = similar_users(1:2,1); % Via buscar os 2 utilizadores mais similares

            allMovies = []; % Este array irá servir para guardar os filmes dos 2 utilizadores mais similares
            for i = 1:length(most2_similarusers)
                similar_userID = most2_similarusers(i,1); % Vai buscar o ID dos utilizadores mais similares
                similar_user_movies = movies{similar_userID}(:,1); % Vaibuscar a lista dos filmes dos utilizadores mais similares
                allMovies = [allMovies ; similar_user_movies]; % Adiciona ao array
            end

            allMovies = unique(allMovies); % Tira os filmes que possam estar duplicados

            userMovies = movies{userID}(:,1); % Vai buscar a lista de filmes do utilizador escolhido
            unrated_movies = setdiff(allMovies,userMovies); % Encontra os filmes que o utilizador escolhido não avaliou mas que os mais similares avaliaram

            fprintf("Movies not rated by user %d but rated by 2 similar users: \n",userID);

            for i = 1:length(unrated_movies)
                movieID = unrated_movies(i);
                movie_name = data{movieID,1}; % Procura o nome do filme  no data
                fprintf("   %d -> %s\n",movieID,movie_name)
            end

        case 3 
            fprintf("----------------------------------------------------------------\n")
            threshold = 0.8;
            rated_movies = movies{userID}(:,1); % Vai buscar os filmes do utilizador escolhido

            selected_movies = selectMovies(sign_styles,data,rated_movies,threshold);
            unique_movies = unique(selected_movies); % Remove os filmes que possam estar repetidos
            movie_count = zeros(length(selected_movies),1); % Array que vai guardar o número de vezes que cada filme aparece em unique_movies
            % Count number of times each movie appears in selected_movies
            % Conta o número de vezes que cada filme aparece no array selecte_movies

            for i = 1:length(unique_movies)
                movie = unique_movies{i};
                movie_count(i) = sum(strcmp(movie,selected_movies));
            end
 
            [max_count, max_index] = max(movie_count); % Encontra o filme e o seu indíce que aparece mais vezes 
            second_max_count = max(movie_count(movie_count ~= max_count)); % Encontra o segundo filme que aparece mais vezes
            second_max_index = find(movie_count == second_max_count); % Encontra o índice do segundo filme que aparece mais vezes

            fprintf("%s\n%s\n",selected_movies{max_index},selected_movies{second_max_index});


        case 4
            fprintf("----------------------------------------------------------------\n")
            string = input("\n|-SEARCH-|\nInsert movie name(partial or complete)\n >>");
            str = lower(string);

            K = 100;
            k = 4;
            for s = 1:length( str ) - k + 1
                shingle = str( s : s + k - 1) ;     % metodo para selecionar shingles de strings
                
                hash = zeros(1,K);
                for hashFunc = 1:K
                    shingle = [shingle int2str(hashFunc)];
                    hash(hashFunc) = DJB31MA(shingle, 127);
                end
                array_Shingles(1,:) = min(hash);
            end

            n_movies = size(Set,1);
            list = zero(1,2);

            ind = 1;
            for movie = 1:n_movies
               dist = sum(Set(movie,1) ~= array_Shingles)/K; 
               if dist < 0.8
                   list(ind,:) = [data(movie,1) dist];
                   ind = ind+1;
               end
            end
            
            search_results = sortrows(list,2);
            for i = 1:5
                fprintf("\nREsults:");
                fprintf("\n   >> %s  -> Jaccard dist = %.6f", data(search_results(i,1)), search_results(i,2));
            end

        otherwise
            fprintf("----------------------------------------------------------------\n")
            fprintf("Not a valid option!\nPlease insert a valid one\n")
    end
    fprintf("----------------------------------------------------------------\n")
    fprintf(options);
    choice = input(prompt);
end
