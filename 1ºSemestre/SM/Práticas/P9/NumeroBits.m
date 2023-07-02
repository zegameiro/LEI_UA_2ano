function NumBits = NumeroBits(texto)
% texto -> String 
% NumBits -> Número de bits necessário para codificar 
   [sym, frequencia] = alfabeto2(texto);
   [freq, i] = sort(frequencia,'descend');
   sym = sym(i);
   NumBits = 0;
  
  for i = 1:length(sym)
    NumBits = NumBits + sum(texto == sym(i)) * i;
  end
end