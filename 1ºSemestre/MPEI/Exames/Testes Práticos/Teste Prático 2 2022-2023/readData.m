%Avaliaçao 2 MPEI - MovieSite
clear
clc
%% importar ficheiros

udata = load("u.data");                             % informação sobre os utilizadores
data = readcell("films.txt",'Delimiter','\t');      % informação sobre os filmes

%% guardar informação do u.data -> usersID, movieID, rating
info = udata(:, 1:3);       % guarda os userID, movieID e avaliação

users = unique(info(:,1));  % array com os userIDs
n_users = length(users);    % nº de users

movies = cell(n_users,1);   % cell array para guardas os filmes por user(1-943)

for user = 1:n_users
    % por casa user
    index = info(:,1) == users(user);
    % vai busar o par movie-rating
    movie_and_rating = [info(index,2) info(index,3)];
    % guarda todos os filmes assistidos pelo utilizador no cell array
    movies{user} = [movies{user} movie_and_rating];
end
clear vars user movie_and_rating udata

% O cell array _movies_ é do tipo userID -> movie1 - RATING   ->MUDAR!!!
%                                           movie2 - RATING
%                                                 ... 
%                                           movieN - RATING

% %%
% n_movies = size(data,1);
% movieInfo = cell(n_movies,2);
% 
% for movie = 1:n_movies
%     % para cada filme coloca o nome deste no cell array movieInfo
%     movieInfo{movie,1} = data{movie,1};
%     genre = [];
% 
%     movieInfo{movie,2} = genre;
%     % percorre todos os generos e guarda num array genre
%     for gen = 2:length(data{movie})
%         movieInfo{movie,2} = [movieInfo{movie,2}, data{movie,gen}];
%     end
%     
%     % coloca o array genre no cell array movieInfo
%     
% end
%     
% %clear movie gen vars genre data

%% op2 -> matriz de assinaturas para cada user
% vamos comparar os filmes que cada user assistiu, quanto mais filmes em
% comum tiverem, mais similares são

% sign -> users x n_hash_functions
sign = minHash(movies,100);

%% op3 -> matriz de assinaturas dos géneros para cada filme

sign_styles = minHash_movieStyles(data,100);

%% MinHash para op 4
% crear um array de shingles de k-comprimento e passar todos os filmes por
% esta função. depois passamos a string e comparamos.
K = 100;
k = 4;

n_movies = size(data,1);

Set = cell(n_movies,2);            % array de minhash_shingles | nª rating > 3
array_Shingles = Inf(1, K);        % array para guardar os minhash dos shingles
wb = waitbar(0, "Getting Shingles Array...");

for m = 1:n_movies
    waitbar(m/n_movies, wb);
    movie = lower(data{m, 1});
    for s = 1:length( movie ) - k + 1
        shingle = movie( s : s + k - 1) ;     % metodo para selecionar shingles de strings
        
        hash = zeros(1,K);
        for hashFunc = 1:K
            shingle = [shingle int2str(hashFunc)];
            hash(hashFunc) = DJB31MA(shingle, 127);
        end
        array_Shingles(1,:) = min(hash);
    end
    Set{m,1} = array_Shingles;
    Set{m,2} = 0;
end
delete(wb);

save allData.mat users movies data Set sign_styles sign














