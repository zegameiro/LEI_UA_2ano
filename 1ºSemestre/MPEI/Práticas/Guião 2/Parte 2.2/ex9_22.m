%% Exercício 9a) Guião 2 (2.2)

clc
clear

N = 1e6;
n = 1;
media = 14;
des_padrao = 2;
notas = 14 + 2 * randn(n,N);
cf = 0;

for i=1:length(notas)
    if (notas(i) >= 12) && (notas(i) <= 16)
        cf = cf + 1;
    end
end

probSim_9a = cf / N;
fprintf("Resultado a) por Simulação = %f \n",probSim_9a)

%% Exercício 9b) Guião 2 (2.2)

cf1 = 0;
for i=1:length(notas)
    if (notas(i)>= 10) && (notas(i) <= 18)
        cf1 = cf1 + 1;
    end
end

probSim_9b = cf1 / N;
fprintf("Resultado b) por Simulação = %f \n",probSim_9b)


%% Exercício 9c) Guião 2 (2.2)

cf2 = 0;

for i=1:length(notas)
    if (notas(i)>= 10)
        cf2 = cf2 + 1;
    end
end

probSim_9c = cf2 / N;
fprintf("Resultado c) por Simulação = %f \n",probSim_9c)

%% Exercício 9d) (para o a)) Guião 2 (2.2)

% 12 - 14  / 2 = -2/2
% 16 - 14  / 2 = 2/2

% P[12<X<16] = P[-2/2 < Z < 2/2] = P[-1 < z < 1] = 
% Fi(1) - Fi(-1) = 1-Q(1) - 1 + Q(-1) = 
% Q(-1) - Q(1) = 1-Q(1) - Q(1) = 
% 1 - 2Q(1)   (2Q(1) = 2 * normcdf(12,14,2))

var1 = normcdf(12,14,2);
proba = 1 - 2 * var1;
fprintf("Valor Téorico para o a) = %f \n",proba)

%% Exercício 9d) (para o b)) Guião 2 (2.2)

% 10 - 14  / 2 = -4/2;
% 18 - 14  / 2 = 4/2;

% P[12<X<16] = P[-4/2 < Z < 4/2] = P[-2 < z < 2] = 
% Fi(2) - Fi(-2) = 1-Q(2) - 1 + Q(-2) = 
% Q(-2) - Q(2) = 1-Q(2) - Q(2) = 1 - 2Q(2) = 
% 1 - 2*0.2275e-1

var2 = normcdf(10,14,2);
probb = 1 - 2 * var2;
fprintf("Valor Téorico para o b) = %f \n",probb)


%% Exercício 9d) (para o c)) Guião 2 (2.2)

var3 = normcdf(10,14,2);
probc = 1 - var3;
fprintf("Valor Téorico para o c) = %f \n",probc)



