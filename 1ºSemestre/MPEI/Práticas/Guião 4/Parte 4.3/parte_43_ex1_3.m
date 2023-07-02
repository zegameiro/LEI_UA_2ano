%% Exercício 1 e 3 Guião 4 (4.3)

clear
clc

% Código base para deteção de pares similares
udata = load('u.data'); % Carrega o ficheiro dos dados dos filmes

% Fica apenas com as duas primeiras colunas
u = udata(1:end,1:2); clear udata;

% Lista de utilizadores
users = unique(u(:,1)); % Extrai os IDs dos utilizadores
Nu = length(users); % Número de utilizadores

% Constrói a lista de filmes para cada utilizador
Set = cell(Nu,1); % Usa células

for n = 1:Nu % Para cada utilizador
    % Obtém os filmes de cada um
    ind = find(u(:,1) == users(n));
    % E guarda num array. Usa células porque utilizador tem um número diferente de filmes. 
    % Se fossem iguais podia ser um array
    Set{n} = [Set{n} u(ind,2)];
end

%% Calcula a distância de Jaccard entre todos os pares pela definição.
t1 = tic();
J = zeros(Nu); % array para guardar distâncias
h = waitbar(0,'Calculating');

for n1 = 1:Nu
    waitbar(n1/Nu,h);
    for n2 = n1+1:Nu
        J(n1,n2) = 1 - length(intersect(Set{n1},Set{n2})) / length(union(Set{n1},Set{n2})); % Calcula a distância de Jaccard e guarda no array
    end
end

delete (h)

fp = fopen("ex1.txt","w");
fprintf(fp,"Tempo para calcular as distâncias de Jaccard = %.4f\n",toc(t1));

%% Com base na distância, determina pares com distância inferior a um limiar pré-definido
threshold = 0.4; % limiar de decisão

% Array para guardar pares similares (user1, user2, distância)
t2 = tic();
SimilarUsers = zeros(1,3);
k= 1;
for n1= 1:Nu
    for n2= n1+1:Nu
        if J(n1,n2) < threshold
            SimilarUsers(k,:)= [users(n1) users(n2) J(n1,n2)];
            k= k+1;
        end
    end
end

fprintf(fp,"Tempo para calcular a Similaridade = %.4f\n",toc(t2));
fprintf(fp,"Número de pares com distâncias inferiores a %.1f = %d\n",threshold,k);
fprintf(fp,"Utilizadores com distância similar:\n");
fprintf(fp,"  %d %d %.4f\n",SimilarUsers');
fclose(fp);


