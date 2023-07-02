function [movies, users, time] = get_info(file, max_users)
%get_info   Recebe o ficheiro completo e retira as informaçoes
% relevantes: UserID e UserMovies
%   USAGE -> [Set, n_users, users, time] = get_info(file)

tic
udata = load(file);    % Carrega o ficheiro dos dados dos filmes

% Fica apenas com as duas primeiras colunas: user e movies
if(max_users == 0)
    u = udata(1:end,1:2);
else
    u = udata(1:max_users,1:2);
end
clear udata;

% Lista de utilizadores
users = unique(u(:,1));          % Extrai os IDs dos utilizadores
n_users= length(users);          % Número de utilizadores

% Constrói a lista de filmes para cada utilizador
movies = cell(n_users,1);            % Usa células
for n = 1:n_users                    % Para cada utilizador
% Obtém os filmes de cada um
    ind = u(:,1) == users(n);        %
    % E guarda num array. Usa células porque utilizador tem um número
    % diferente de filmes. Se fossem iguais podia ser um array
    movies{n} = [movies{n} u(ind,2)];
end

time = toc;

end