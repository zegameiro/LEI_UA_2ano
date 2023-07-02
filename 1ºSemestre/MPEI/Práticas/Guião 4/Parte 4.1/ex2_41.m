%% Exercício 2 a) e b) Guião 4 (4.1)
clear
clc
%%  a) e b)

% values
num_keys = 1e5;
alphabet = ['a':'z' 'A':'Z'];
sizes = [5e5 1e6 2e6];          % HashTable sizes

for T = sizes
    fprintf("\n SIZE = %d \n", T)
    % create 'HashTable'
    table = zeros(T,1);
    
    % Generate keys
    keys = generate_keys_unif(num_keys,6,20,alphabet);
    
    % b) -> Get attr array info
    attr = zeros(T,1);
    
    tic
    % Convert and insert keys into 'HashTable'
    for k = 1:num_keys
        key = keys{k};
    
        % Convert string to hash
        hash = string2hash(key);
    
        % Assure that hash < T
        hash = rem(hash,T);
    
        % Put hash in HashTable
        table(k) = hash;
    
        % B) -> Count number of attribuitions to that hash    
        attr(hash) = attr(hash) + 1; 
    
        % fprintf("%-20s -> %d\n ",key, hash)
    end
    toc
    
    % b) -> get num of colisions
    n_colisoes = sum(attr > 1);
    percent = (n_colisoes/T)*100;
    
    fprintf("Existem %d colisões\nPercentagem de colisão = %f\n", n_colisoes, percent);
end

