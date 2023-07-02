function [x,t] = fourier(Ta,f0,Np,ak,bk)
% Ta - Período de amostragem /s
% f0 - Frequência do sinal composto /hz
% Np - Número de períodos a consideraar para o sinal resultante
% ak - Vetor (k x 1) com os valores de ak da série
% bk - Vetor (k x 1) com os valores de bk da série
% x - Vetor com um conjunto de amostras de um sinal
% t - Vetor tempo

N = round(Np / (f0 * Ta)); % Número de períodos
t = (0:N-1) * Ta;

counta = 0;
countb = 0;

for i = 1:length(ak)
    counta = counta + (ak(i) * cos(2 * pi * (i-1) * f0 * t));
    countb = countb + (bk(i) * sin(2 * pi * (i-1) * f0 * t));
end

x = counta + countb;

end