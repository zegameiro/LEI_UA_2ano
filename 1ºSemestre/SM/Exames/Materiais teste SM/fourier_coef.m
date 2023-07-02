function [ak,bk] = fourier_coef(Ta,T0,x,K)
% Ta - Período de amostragem /s
% T0 - Período do sinal /s
% 𝑥 - Vetor (𝑁x1) com as amostras sucessivas do sinal a decompor (deverá ser passado 
% um número inteiro de períodos deste sinal, não devendo o último período ficar 
% truncado);
% K - Número de harmónicas a considerar na decomposição

f = 1 / T0;
N= length(x);
t = [(0:N-1)]'*Ta;
ak = zeros(K+1,1);
bk= zeros(K+1,1);
ak(1) = mean(x);    % Média do x
bk(1) = 0;

for k = 2:K+1
      ak(k) = ((2 / N) * x * cos(2 * pi * (k-1) * f * t));
      bk(k) = ((2 / N) * x * sin(2 * pi * (k-1) * f * t));
end

end