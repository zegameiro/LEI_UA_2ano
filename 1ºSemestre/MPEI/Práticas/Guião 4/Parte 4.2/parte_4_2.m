%% Exercício 1 Guião 4 (4.2)

clc
clear

fid = fopen('wordlist-preao-20201103.txt','r');
dicionario = textscan(fid,'%s');  % Cria um cell array com um único cell em que estão presentes todoas as palavras
fclose(fid);
dicionario = dicionario{1};  % SUbstitui o cell array com um único cell por um cell array com vários cell's com todas as palavras

k = 3;  % Número de hashfunctions utilizadas
n = 8000;
B = bloomFilter_initializer(n);

for i = 1:1000
    B = bloomFilter_insertion(B,dicionario{i},k);
end

count = 0;

for j = 1001:11000   % Ir buscar as 10000 mil palavras que são diferentes
    if bloomFilter_verification(B,dicionario{j},k) == true
        count = count + 1;
    end
end
media = count / 10000 * 100;

fprintf("Em média existem %.4f falsos positivos\n",media)

%% Exercício 4 Guião (4.2)

m = 10000;
false_positives_teoric = (1 - exp((- k * m)/n))^k;

%% Exercício 5 Guião 4 (4.2)

clc
clear

fid = fopen('wordlist-preao-20201103.txt','r');
dicionario = textscan(fid,'%s');  % Cria um cell array com um único cell em que estão presentes todoas as palavras
fclose(fid);
dicionario = dicionario{1};  % SUbstitui o cell array com um único cell por um cell array com vários cell's com todas as palavras

k = [3 4 5 6 7 8 9 10];
n = 8000;
B = bloomFilter_initializer(n);
false_positives = zeros(1,length(k));

for a = 1:length(k)
    for i = 1:1000
        B = bloomFilter_insertion(B,dicionario{i},k(a));
    end
    
    count_fp = 0;
    
    for j = 1001:11000   % Ir buscar as 10000 mil palavras que são diferentes
        if bloomFilter_verification(B,dicionario{j},k(a)) == true
            count_fp = count_fp + 1;
        end
    end
    false_positives(a) = count_fp / 10000 * 100;
end

figure(1)
plot(k,false_positives,".-")
xlabel("k (número de hash functions a utilizar)")
ylabel("Probabilidade de falsos positivos")
grid on

