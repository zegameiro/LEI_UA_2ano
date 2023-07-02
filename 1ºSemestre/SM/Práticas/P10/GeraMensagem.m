function [numBits,numBPS] = GeraMensagem(f,compMes,nBits)
% f -> vetor de dimensão N*1 com as frequências dos símbolos
% compMes -> Número total de símbolos usados na mensagem
% nBits -> vetor de dimensão N*1 com o número de bits com que é codificado
%          cada símbolo
% numBits -> Número total de bits que a mensagem gerada aleatoriamente
%            requer ser representada
% numBPS -> Número médio de bits por símbolo verificado na mensagem gerada

    mesg = randsample(1:length(f),compMes,true,f/sum(f));

    size = length(f);
    numBits = 0;
    for i = 1:size
        numBits = numBits + sum(mesg == i) * nBits(i);
    end
    numBPS = numBits / compMes;

end