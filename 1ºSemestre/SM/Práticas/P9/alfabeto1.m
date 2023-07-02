function simbolos = alfabeto1(texto)
% simbolos -> lista de caracteres
% texto -> string
    n = 1;
    simbolos(n) = texto(1);
    for k = 2:length(texto)
        if sum(simbolos == texto(k)) == 0
            n = n + 1;
            simbolos(n) = texto(k);
        end
    end

end