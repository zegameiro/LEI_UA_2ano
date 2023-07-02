function SimilarUsers = getSimilaresHash(users,assinaturas,threshold,numHash)
% assinaturas -> matriz com as assinaturas
% users -> matriz com os dados dos utilizadores
% threshold -> valor dado no enunciado
% numHash -> Número de hash functions a utilizar

    Nu = length(users);
    % Array para guardar pares similares (user1, user2, distancia)
    SimilarUsers= zeros(1,3);
    k= 1; 
    
    h = waitbar(0,'Calculating');
    tic
    for n1= 1:Nu
        for n2= n1+1:Nu
            distancia = sum(assinaturas(n1,:) ~= assinaturas(n2,:))/numHash; % Calcula a distância de Jaccard
            if  distancia < threshold
                SimilarUsers(k,:)= [n1 n2 distancia];
                k= k+1;
            end
        end
    end
    toc
    delete(h);

end

