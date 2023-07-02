%% Exercício 6 a) Guião 3 (3.1)

clc
clear

disp("a) -------------------")

Tji = [0.8 , 0.2 , 0 , 0 , 0 ;
       0 , 0.6 , 0.3 , 0.1 , 0 ;
       0 , 0 , 1 , 0 , 0 ;
       0.3 , 0.2 , 0 , 0.4 , 0.1 ;
       0 , 0 , 0 , 0 , 1]'

%% Exercício 6 b) Guião 3 (3.1)

disp("b) -------------------")

y1 = [1;0;0;0;0]; % Vetor inicial, ou seja começa no estado 1
n1 = zeros(1,100);
n1(1) = 0;
x1 = 1:100;

for i = 2:100
    y1 = Tji * y1;
    n1(i) = y1(2);
end

plot(x1,n1)
xlabel("Número de transições")
ylabel("Probabilidade")
hold on
grid on

%% Exercício 6 c) Guião 3 (3.1)

disp("c) -------------------")

y2 = [1;0;0;0;0];
n2 = zeros(1,100);
n2(1) = 0;
x2 = 1:100;

for i = 2:100
    y2 = Tji * y2;
    n2(i) = y2(3);
end

plot(x2,n2)
legend("Prob. estado 2","Prob. estado 3");

y3 = [1;0;0;0;0];
n3 = zeros(1,100);
n3(1) = 0;
x3 = 1:100;

for i = 2:100
    y3 = Tji * y3;
    n3(i) = y3(5);
end

plot(x3,n3)
legend("Prob. estado 2","Prob. estado 3","Prob. estado 5");

%% Exercício 6 d) Guião 3 (3.1)

disp("d) -------------------")

T_can = [0.8 , 0 , 0.3 , 0 , 0;         % 1
         0.2 , 0.6 , 0.2 , 0 ,0;        % 2
         0 , 0.1 , 0.4 , 0 , 0;         % 4
         0 , 0.3 , 0 , 1 , 0;           % 3
         0 , 0 , 0.1 , 0 , 1];          % 5
% Matriz Canónica

Q = T_can(1:3,1:3)

%% Exercício 6 e) Guião 3 (3.1)

disp("e) -------------------")

I = eye(3);   % Gera a matriz identidade de 3x3

F = (I - Q)^(-1)    % Matriz Fundamental

%% Exercício 6 f) Guião 3 (3.1)

disp("f) -------------------")

val_esp = F' * ones(3,1);

fprintf("\nA média do nº de passos até à absorção:\n começando no estado 1 é %d \n começando no estado 2 é %d \n começando no estado 4 é %d \n", val_esp)

%% Exercício 6 g) Guião 3 (3.1)

disp("g) -------------------")

R = T_can(4:5, 1:3);
B = R*F; % Coluna -> comecar em estado transitorio ; Linha -> acabar no estado absovente
fprintf('\nProbabilidade (estado 3) = %f ;\nProbabilidade(estado 5) = %f \n',B(1,1),B(2,1));
 