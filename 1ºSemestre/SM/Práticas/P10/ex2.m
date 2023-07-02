%% Exercício 2 Guião 9

f = [14 64 5 10 7];
comMesg = 10000;
nBits = [2 1 3 4 4];

[numBits,numBPS] = GeraMensagem(f,comMesg,nBits);

fprintf("Número de bits = %.4f\nNúmero Médio de Bits = %.4f",numBits,numBPS/comMesg)