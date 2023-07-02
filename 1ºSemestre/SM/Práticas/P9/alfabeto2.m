function [simbolos,frequencia] = alfabeto2(texto)
% texto -> string
% f -> frequencia de cada símbolo
% simbolos -> lista de caracteres
    n = 1;
    simbolos(n) = texto(1);
    for k = 2:length(texto)
        if sum(simbolos == texto(k)) == 0
            n = n + 1;
            simbolos(n) = texto(k);
        end
    end
    
    frequencia = zeros(length(simbolos),1);
    for i = 1:length(simbolos)
        for j = 1:length(texto)
            if simbolos(i) == texto(j)
                frequencia(i) = frequencia(i) + 1;
            end
        end
    end
end