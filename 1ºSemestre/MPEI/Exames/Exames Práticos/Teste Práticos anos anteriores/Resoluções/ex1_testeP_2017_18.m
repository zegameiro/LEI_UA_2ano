%% Exercício 1a) Miniteste 2017/18

clear
clc

fprintf("1a)-------------------------------------------------------------\n")

% 1 -> Estado sem erros | 2 -> estado com 1 erro | 3 -> estado com 2 ou mais erros
%        1     2     3
T = [ 0.90 , 0.5 , 0.5 ;   % 1
      0.09 , 0.4 , 0.4 ;   % 2
      0.01 , 0.1 , 0.1 ]   % 3

v0 = [0 ; 0 ; 1]

%% Exercício 1b) Miniteste 2017/18

fprintf("1b)-------------------------------------------------------------\n")

prob = T^4 * v0;

fprintf("Sem erros: %.4f\n",prob(1))
fprintf("Com 1 erro: %.4f\n",prob(2))
fprintf("Com 2 ou mais erros: %.4f\n",prob(3))

%% Exercício 1c) Miniteste 2017/18

fprintf("1c)-------------------------------------------------------------\n")

xn = v0;

iter = 1;

while 1
    x_ant = xn;
    xn = T^iter * v0;

    if max(abs(xn - x_ant)) < 1e-3
        break;
    end

    iter = iter + 1;
end

fprintf("Probabilidade de perda de pacote = %.4f\n",xn(3))
fprintf("Probabilidade de perda de pacote sem o código corretor de erros = %.4f\n",1-xn(1))

