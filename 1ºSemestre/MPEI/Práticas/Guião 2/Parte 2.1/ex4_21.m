%% Exercício 4 a) Guião 2 (2.1)

N = 1e6;  % Número de Experiências
probSim_4a = 0; % Probabilidade de duas pessoas terem a mesma data de aniversários
n = 1; % Número de Pessoas
dias = 365;

while probSim_4a < 0.5
    n = n + 1;
    birthdays = randi(dias,n,N);
    count = 0;
    for i = 1:N
        if length(unique(birthdays(:,i))) < n
            count = count + 1;
        end
    end
    
    probSim_4a = count / N;
end

str = ['O menor número de pessoas é (prob = 0.5) ' num2str(n)];
disp(str)

probSim_4b = 0;
n1 = 0;

%% Exercício 4 b) Guião 2 (2.1)

while probSim_4b < 0.9
    n1 = n1 + 1;
    birthdays = randi(dias,n1,N);
    count = 0;
    for i = 1:N
        if length(unique(birthdays(:,i))) < n1
            count = count + 1;
        end
    end
    
    probSim_4b = count / N;
end

str = ['O menor número de pessoas é (prob = 0.9) ' num2str(n1)];
disp(str)

