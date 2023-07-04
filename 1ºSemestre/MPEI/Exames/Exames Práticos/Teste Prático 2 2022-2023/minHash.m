function sign = minHash(data,numHash)
%MINHASH Summary of this function goes here
%   Detailed explanation goes here

n_users = size(data,1);   %numero de users -> nºde linhas

%Nº linhas -> Nº users, Nº colunas -> Nº hash functions
sign = Inf(n_users, numHash);  
wb = waitbar(0,'Getting MinHash...');

for user = 1:n_users
    waitbar(user/n_users,wb);
    n_movies = length(data{user});   %nº de filmes vistos pelo user

    for movie = 1:n_movies
        key = int2str(data{user,1}(movie,1));
        hashCodes = DJB31MA_mod(key,127,numHash);
        sign(user,:) = min(hashCodes,sign(movie,:));
    end
end

delete (wb)
end
