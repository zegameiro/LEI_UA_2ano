%Guião P04.3 MPEI
clear
clc
%%  1)
fprintf("\n1 + 2.\n");

% Extrai os dados do ficheiro

max_users = 100;
[movies, users, create_info] = get_info('u.data', max_users);

% Calcula a distância de Jaccard entre todos os pares pela definição.
[J, calc_jaccard_dist] = jaccard_dist(movies);

save("jaccard_dist.mat", "J");      %guarda as d.j no ficheiro jaccard_dist.mat

% Com base na distância, determina pares com distância inferior a um limiar pré-definido
threshold = 0.4 ;        % limiar de decisão

% Array para guardar pares similares (user1, user2, distância)

[SimilarUsers, get_similar_users] = find_similar_users(J,threshold, users);

% Resultados
fprintf("\n======================== TIMES ========================\n")
fprintf("\n Time needed to create user files            = %.4fs", create_info);
fprintf("\n Time needed to calculate Jaccard's distance = %.4fs", calc_jaccard_dist);
fprintf("\n Time needed to get similar users            = %.4fs\n", get_similar_users);

fprintf("\n================SIMILAR USERS================")
fprintf("\n%-10s %-10s %-30s\n", "user1", "user2", "jaccard's distance")
for i = 1:size(SimilarUsers,1)
    line = SimilarUsers(i, :);
    fprintf("%-10d %-10d %-22.8f\n",line(1), line(2), line(3));
end

clear
%% 3.
fprintf("\n3.\n");

% Extrai os dados do ficheiro
[movies, users, create_info] = get_info('u.data', 0);

% Calcula a distância de Jaccard entre todos os pares pela definição.
[J, calc_jaccard_dist] = jaccard_dist(movies);

save("jaccard_dist.mat", "J");      %guarda as d.j no ficheiro jaccard_dist.mat

% Com base na distância, determina pares com distância inferior a um limiar pré-definido

threshold = 0.4 ;        % limiar de decisão

% Array para guardar pares similares (user1, user2, distância)

[SimilarUsers, get_similar_users] = find_similar_users(J,threshold, users);

% Resultados
fprintf("\n======================== TIMES ========================\n")
fprintf("\n Time needed to create user files            = %.4fs", create_info);
fprintf("\n Time needed to calculate Jaccard's distance = %.4fs", calc_jaccard_dist);
fprintf("\n Time needed to get similar users            = %.4fs\n", get_similar_users);

fprintf("\n================SIMILAR USERS================")
fprintf("\n%-10s %-10s %-30s\n", "user1", "user2", "jaccard's distance")
for i = 1:size(SimilarUsers,1)
    line = SimilarUsers(i, :);
    fprintf("%-10d %-10d %-22.8f\n",line(1), line(2), line(3));
end

%% 4.) -> MinHash
fprintf("\n4.\n");

K = [50,100,200];                   % nº de funçoes de hash
MAX_USERS = [100, 300, 500, 0];     % nº de users

for k = K
    for max_users = MAX_USERS

        % Extrai os dados do ficheiro
        [movies, users, create_info] = get_info('u.data', 0);
        
        % Formata a matriz para calcular o array de assinaturas
        tic
        matrix = makeMatrix(movies);
        sign = minHash(matrix, k);

        dist = zeros(size(sign,2));
        sim_users = [];

        h = waitbar(0,'Calculating');
        for user1 = 1:size(sign,2)
            waitbar(user1/size(sign,2),h);
            for user2 = user1+1:length(sum_sign)
                dist(user1,user2) = sum(sign(:,user1)==sign(:,user2))/k;
            end
        end

        get_aprox_jaccard_dist = toc;
        
        % Calcula a distância de Jaccard entre todos os pares pela definição.
        [J, calc_jaccard_dist] = jaccard_dist(movies);
        

        FILE_NAME = strjoin(["jaccard_dist_" k  "_" max_users ".mat"],'');

        save(FILE_NAME, "J");      %guarda as d.j no ficheiro jaccard_dist_k_maxusers.mat

        % Calcular similar users
        [SimilarUsers, get_similar_users] = find_similar_users(J, threshold, users);

        
        % Resultados
        fprintf("\n=================================== TIMES ===================================\n")
        fprintf("\n Time needed to create user files                                  = %.4fs", create_info);
        fprintf("\n TIme needed to calculate an aprox to jaccard dist with sign array = %.4f",get_aprox_jaccard_dist);
        fprintf("\n Time needed to calculate Jaccard's distance                       = %.4fs", calc_jaccard_dist);
        fprintf("\n Time needed to get similar users (jaccasrd)                       = %.4fs\n", get_similar_users);
        fprintf("\n Time needed to get similar users (signature)                      = %.4fs\n", get_similar_users);

        
        fprintf("\n================JACCARD DIST================            ===================APROX===================")
        fprintf("\n%-10s %-10s %-30s\n", "user1", "user2", "jaccard's distance")
        for i = 1:size(SimilarUsers,1)
            line = SimilarUsers(i, :);
            fprintf("%-10d %-10d %-22.8f\n",line(1), line(2), line(3));
        end


    end
end














