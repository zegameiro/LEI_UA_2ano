%% Exercício 1a) Guião 3 (3.1)
clear
clc

disp("a) -------------------")

T = [0.2 , 0.3 ;  % Probabilidade de ir à aula: 0.7 - Esteve presente na aula anterior; 0.8 - Faltou à aula anterior
     0.8 , 0.7];  % Probabilidade de não ir à aula: 0.3 - Esteve presente na aula anterior; 0.2 - Faltou à aula anterior

x0 = [0 ; 1];  % Vetor inicial (esteve presente na primeira aula)

x1 = T * x0;
x2 = T^2 * x0;
prob_1a = x2(2)  % Probabilidade de estar presente na próxima aula

%% Exercício 1b) Guião 3 (3.1)

disp("b) -------------------")

T = [0.2 , 0.3 ;
     0.8 , 0.7];

x0 = [1 ; 0];  % Vetor inicial (esteve presente na primeira aula)
x2 = T^2 * x0;

prob_1b = x2(2)  % Probabilidade de estar presente na aula da próxima quarta

%% Exercício 1c) Guião 3 (3.1)

disp("c) -------------------")

T = [0.2 , 0.3 ;
     0.8 , 0.7];  

x0 = [1 ; 0]; % Vetor inicial (não esteve presente na 1ª aula mas está na 2ª)
x29 = T^29 * x0; 

prob_1c = x29(2)


%% Exercício 1d) Guião 3 (3.1)

disp("d) -------------------")

v = [0.15; 0.85];
prob_1d = zeros(1,30);
prob_1d(1) = 0.15;

for i = 1:30 - 1 
    v = T * v;
    prob_1d(i) = v(1);
end

plot(prob_1d)
grid on
  




