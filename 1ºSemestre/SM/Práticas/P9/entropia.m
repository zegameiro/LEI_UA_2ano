function H = entropia(texto)
% texto -> String
% H -> Valor da entropia
    [Simbolos,Frequencia] = alfabeto2(texto);
    H = 0;
    for i=1:length(Simbolos)
        H = H + (Frequencia(i)/100) * log2(Frequencia(i)/100);
    end
    H = -H
end