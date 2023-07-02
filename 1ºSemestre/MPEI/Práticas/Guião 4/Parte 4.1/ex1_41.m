%% Exercício 1 a) Guião 4 (4.1)

clear
clc

n_keys = 1e5;
alphabet = ['a':'z' 'A':'Z'];
imin = 6;
imax = 20;

gen_keys = generate_keys_unif(n_keys,imin,imax,alphabet);

%% Exercício 1 b) Guião 4 (4.1)

prob_letters = load("prob_pt.txt"); % Probabilidade de cada letra
imin = 6;
imax = 20;
alphabet = ['a':'z'];

for i = 1:length(alphabet)
    a(i) = discrete_rnd(alphabet,prob_letters);
end

gen_keys2 = generate_keys_unif(n_keys,imin,imax,a)


