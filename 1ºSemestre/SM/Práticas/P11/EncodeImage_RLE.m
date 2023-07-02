function stream = EncodeImage_RLE(image)
% stream -> vetor com a informação da imagem comprimida
% image -> array com a informação da imagem
    [N,M] = size(image);
    count = uint8(0);
    k = 1;
    stream(k,1) = image(1,1);
    for n=1:N  % Percorrer todas as linhas
        for m=1:M  % Percorrer todas as colunas
            if image(n,m) == stream(k,1)
                count = count + 1;
                if count == 255
                    k = k + 1;
                    stream(k,1) = count;
                    k = k+1;
                    stream(k,1) = image(n,m);
                    count = 1;
                end
            else
                k = k + 1;
                stream(k,1) = count;
                k = k+1;
                stream(k,1) = image(n,m);
                count = 1;
            end
        end
    end
    k = k+1;
    stream(k,1) = count;
end