function hashCodes = DJB31MA_mod(key, seed, numHash)
% implementação da hash function DJB31MA com base no algoritmo obtido
% no resumo 2014(PJF) que está em C
%
%  chave        array de caracteres com a chave
%  seed         semente que permite obter vários hash codes para a mesma chave
%   
%  hashCodes    array com a key depois de k funções de hashing
len = length(key);
key= double(key);
h = seed;

for i=1:len
    h = mod(31 * h + key(i), 2^32 -1) ;
end

% modded part
hashCodes = zeros(1, numHash);             % array para guardar os NUMHASH hashcodes criados

for hashNum = 1:numHash
    h = mod(31 * h + hashNum, 2^32 -1); % metodo de hashing para ints
    hashCodes(hashNum) = h;
end
end


