function [SimilarUsers, time] = find_similar_users(J, threshold, users)
%find_similar_users     Verifica se a distancia de Jaccard entre dois users
% é menor que um threshold e caso se verifique coloca no array SimilarUsers 
% esses users e a sua distancia de Jaccard. 
%   USAGE -> [SimilarUsers, get_similar_users] = find_similar_users(J, n_users, users)

tic
k = 1;
SimilarUsers= zeros(1,3);           % array de users similares
n_users = length(users);

for i = 1:n_users
    for j = i+1:n_users
        if J(i,j) < threshold           % verifica se a dist_jacc é menor que o threshold
            SimilarUsers(k,:) = [users(i) users(j) J(i,j)];     %se sim coloca no array
            k= k+1;
        end
        
    end
end
time = toc;

end