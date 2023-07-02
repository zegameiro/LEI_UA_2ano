%% Exercício 1 a) Guião 9

clear
clc

letters = ['A','B','C','D','E'];
frequency = [0.14,0.64,0.05,0.10,0.07];
H = 0;

for i = 1:length(letters)
    H = H + frequency(i) * log2(1/frequency(i));
end

disp(H)


