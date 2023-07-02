function Image = DecodeImage_RLE(N,M,Stream)
% N -> Número de colunas
% M -> Número de linhas
% Stream -> array com a informação de uma imagem compressada
    
    Image = uint8(zeros(N,M));

    n = 1; m = 1;
    for i = 2:2:length(Stream) % Percorre todos os números de ocorrências
        for j = 1:Stream(i) % Novo loop que corre tantas vezes quanto o número de ocorrências iterado
            Image(n,m) = Stream(i-1); % O respetivo símbolo encontra-se na posição anterior
            m = m + 1; % Percorrer para a casa seguinte
            if (m > M) % Se o número de coluna no contador for superior ao número de colunas da matriz
                n = n + 1; % Avançar para a linha seguinte
                m = 1; % Resetar o contador para a primeira coluna da linha
            end
        end
    end
end