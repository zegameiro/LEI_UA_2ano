%% Exercício 4 Guião 8
load("Mensagem.mat")
[simbolos2, f] = alfabeto2(Mensagem);

for i = 1:length(f)
    fprintf("\n Símbolo %s, frequência = %d \n",simbolos2(i),f(i))
end