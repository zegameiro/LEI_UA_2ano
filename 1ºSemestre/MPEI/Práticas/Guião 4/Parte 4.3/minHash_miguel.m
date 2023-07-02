function assinaturas = minHash(Set,numHash)
%MINHASH Summary of this function goes here
%   Detailed explanation goes here

Nu = length(Set);   %Nº elementos do Set (users)

%Nº linhas -> Nº users, Nº colunas -> Nº hash functions
assinaturas = Inf(Nu,numHash);  
h = waitbar(0,'Calculating');

tic
for i = 1:Nu
    waitbar(i/Nu,h);
    Nfilmes = length(Set{i});   %Nº filmes do user
    for j = 1:Nfilmes
        key = num2str(Set{i}(j));
        h_out = DJB31MA_mod(key,127,numHash);
        assinaturas(i,:) = min(h_out,assinaturas(i,:));

        %for k = 1:numHash
           % key = [key num2str(k)];
            %hash = DJB31MA(key,127);
            %if hash < assinaturas(i,k)
                %assinaturas(i,k) = hash;
            %end
        %endnumHash
    end
end
delete (h)
toc

end
