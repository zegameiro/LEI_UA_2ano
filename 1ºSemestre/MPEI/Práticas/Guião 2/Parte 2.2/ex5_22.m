%% Exercício 5 Guião 2 (2.2)

clear
clc

% Cálculo da probabilidade de cair um avião com 2 motores
% Despenha-se quando ambos os motores falharem

n = 2;  % Número de motores
k = 2;  % Número de motores a falhar 
p = logspace(-3,log10(1/2),100);

p2Motores = nchoosek(n,k).*p.*k.*(1-p).^(n-k);

% Cálculo da probabilidade de cair um avião com 4 motores

p4Motores = 0;
n = 4;   % número de motores

for i = 3:4 % Podem falhar 3 ou 4 motores
    p4Motores = p4Motores + nchoosek(n,i).*p.^i.*(1-p).^(n-i);
end

figure(1);

plot(p,p4Motores)
hold on
plot(p,p2Motores)

legend('4 motores','2 motores')
xlabel('p')
ylabel('pX')
title('Variacao de pX em funcao de p')

hold off

% Até os dois gráficos se intercetarem (analisando grafico, a mais ou menos
% = 0.33) e mais seguro viajar no avião com 4 motores.
% A partir porém, e preferível viajar no avião com 2 motores.
