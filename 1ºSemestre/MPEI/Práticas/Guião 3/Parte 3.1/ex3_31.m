%% Exercício 3a) Guião 3 (3.1)

clear
clc

disp("a) -------------------")

T = zeros(20);

for i = 1:20
    A = rand(20,1);
    A = A / sum(A);
    T(:,i) = A;
end

sum(T)  % Como é igual a 1 logo a matriz T é estocástica

%% Exercício 3b) Guião 3 (3.1)

disp("b) -------------------")

x = zeros(1,20)';
x(1) = 1;

values = [2 , 5 , 10 , 100];
y = zeros(1,4);

for i = 1:length(values)
    z = T^values(i) * x;
    y(i) = z(20);
end

fprintf("\nApós 2 iterações a probabilidade de estar no estado 20 é %.8f \n",y(1)*100);
fprintf("Após 5 iterações a probabilidade de estar no estado 20 é %.8f \n",y(2)*100);
fprintf("Após 10 iterações a probabilidade de estar no estado 20 é %.8f \n",y(3)*100);
fprintf("Após 100 iterações a probabilidade de estar no estado 20 é %.8f \n",y(4)*100);

