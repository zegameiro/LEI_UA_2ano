%% Exercício 1a) Miniteste 2 2015/16

clear
clc
fprintf("1a)-------------------------------------------------------------\n")
m_xy = [ 0.01 , 0.07 , 0.02 ;
         0.04 ,  0   , 0.08 ;
         0.04 , 0.15 , 0.09 ;
         0.01 , 0.20 , 0.01 ];

soma_probs = sum(sum(m_xy));
beta = 1 - soma_probs; % 1 é o valor que tem que dar ao somar todas as probabilidades
fprintf("O valor de beta é %.2f\n",beta);
beta_index = find(m_xy == 0);
m_xy(beta_index) = beta;

%% Exercício 1b) Miniteste 2 2015/16
fprintf("1b)-----------------------------------------------------------------------\n")

rows = height(m_xy); % Número de linhas
x_marg = zeros(rows,1);

% Calcular as funções de probabilidade marginais de x, basta somar os valores de cada linha
for i = 1:rows
    soma = sum(m_xy(i,:));
    x_marg(i,1) = soma;
end

fprintf("| x || P[X = x] |\n")
fprintf("| 1 ||   %.2f   |\n",x_marg(1))
fprintf("| 2 ||   %.2f   |\n",x_marg(2))
fprintf("| 3 ||   %.2f   |\n",x_marg(3))
fprintf("| 4 ||   %.2f   |\n",x_marg(4))

% Calcular as funções de probabilidade marginais de y, basta somar os valores de cada coluna
y_marg = sum(m_xy);

fprintf("\n| y || P[Y = y] |\n")
fprintf("| 0 ||   %.2f   |\n",y_marg(1))
fprintf("| 2 ||   %.2f   |\n",y_marg(2))
fprintf("| 3 ||   %.2f   |\n",y_marg(3))

%% Exercício 1c) Miniteste 2 2015/16

fprintf("1c)-----------------------------------------------------------------------\n")
columns = width(m_xy); % Número de colunas

media_X = 0;
for j = 1:rows
    media_X = media_X + j * x_marg(j);
end
% Equivalente a media_X = (1 * x1) + (2 * x2) + (3 * x3) + (4 * x4)


var_X = 0;
for i = 1:rows
    var_X = var_X + ((i - media_X)^2 * x_marg(i));
end
% Equivalente a var_X = (1 - media_X)^2 * x1 +(2 - media_X)^2 * x2 + (3 - media_X)^2 * x3 + (4 - media_X)^2 * x4

fprintf("Média de X = %.2f\n",media_X)
fprintf("Variância de X = %.2f\n",var_X)

media_Y = 0;
for i = 1:columns
    y_margin = y_marg(i);
    if i == 1
        i = 0;
    end
    media_Y = media_Y + i * y_margin;
end
% Equivalente a media_Y = (0 * y0) + (2 * y2) + (3 * y3)

var_Y = 0;
for j = 1:columns
    y_margin = y_marg(i);
    if j == 1
        j = 0;
    end
    var_Y = var_Y + ((j - media_Y)^2 * y_margin);
end
% Equivalente a var_Y = (0 - media_Y)^2 * y0 +(2 - media_Y)^2 * y2 + (3 - media_Y)^2 * y3

fprintf("Média de Y = %.2f\n",media_Y)
fprintf("Variância de Y = %.2f\n",var_Y)

%% Exercício 1d) Miniteste 2015/16

fprintf("-----------------------------------------------------------------------\n")

media_XY = (1 * 0 * 0.01) + (2 * 0 * 0.04) + (3 * 0 * 0.04) + (4 * 0 * 0.01) + (1 * 2 * 0.07) + (2 * 2 * beta) + (3 * 2 * 0.15) + (4 * 2 * 0.20) + (1 * 3 * 0.02) + (2 * 3 * 0.08) + (3 * 3 * 0.09) + (4 * 3 * 0.01);

if media_XY == (media_X * media_Y)
    fprintf("As variáveis X e Y são independentes\n")
else
    fprintf("As variáveis X e Y não são independentes\n")
end

%% Exercício 1e) Miniteste 2015/16

% Cálculo da covariância de XY
cov_XY = media_XY - (media_X * media_Y);
% Cálculo do coeficiente de correlação de XY
corr_XY = cov_XY / (sqrt(var_X * var_Y));


