%% Exercício 2a) Guião 3 (3.2)

clear
clc

fprintf("2a)----------------------------------\n")
%      9      1      3      3     5      9     FIM
T = [   0 ,    0 ,    0 ,    0 ,    0 ,    0 ,  0   % 9
      0.5 ,    0 ,    0 ,    0 ,    0 ,    0 ,  0   % 1
      0.5 ,    0 ,    0 ,    0 ,    0 ,    0 ,  0   % 3
        0 ,  1/3 ,    0 ,  0.2 ,  0.1 ,    0 ,  0   % 3
        0 ,  1/3 ,  0.6 ,  0.5 ,  0.4 ,  0.3 ,  0   % 5
        0 ,  1/3 ,  0.4 ,    0 ,  0.3 ,  0.3 ,  0   % 9
        0 ,    0 ,    0 ,  0.3 ,  0.2 ,  0.4 ,  0]  % FIM

%% Exercício 2b) e 2c) Guião 3 (3.2)

fprintf("2b) e c)----------------------------------\n")

N = 1e4;  % Número de experiências

seq7_91_9 = 0;   % Casos favoráveis para a alínea b)
seq7_91 = 0;     % Casos favoráveis para a alínea c)

aux = crawl(T,1,7);

for i = 1:N
    aux = crawl(T,1,7);  % Gerar as sequências

    if length(aux) == 7  % Se o comprimento da sequência == 7
        if aux(1,2) == 2 % A sequência começa por 91
            seq7_91 = seq7_91 + 1;
            if aux(1,end-1) == 6 % A sequência começa em 91 e acaba em 9
               seq7_91_9 = seq7_91_9 + 1;
            end
        end
    end
end

probSim_2b = seq7_91_9 / N;
probSim_2c = seq7_91 / N;

fprintf("\nA probabilidade de uma sequência começar com 91 e terminar em 9 é %.4f \n",probSim_2b)
fprintf("A probabilidade de uma sequência começar com 91 é %.4f \n",probSim_2c)
