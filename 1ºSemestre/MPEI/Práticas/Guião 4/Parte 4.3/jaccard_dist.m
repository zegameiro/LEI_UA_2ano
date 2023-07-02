function [J, time] = jaccard_dist(movies)
%jaccard_dist   Calcula a distancia de Jaccard entre todos os utilizadores no
% array movies e guarda o array J e no ficheiro jaccard_dist.
%   USAGE -> [dist_jaccard, time] = jaccard_dist(Set)

tic
n_users = size(movies,1);                   % nº de users
J = zeros(n_users);                         % array para guardar distâncias
h = waitbar(0,'Calculating');

for i = 1:n_users
    waitbar(i/n_users,h);

    for j = i+1:n_users
        dist_jaccard = 1 - (length(intersect(movies{i},movies{j}))/length(union(movies{i},movies{j})));  %calcula a distancia de jaccard
        J(i, j) = dist_jaccard;                         % coloca a d.j no array
    end
end
clear vars i j;

delete (h)
time = toc;

end