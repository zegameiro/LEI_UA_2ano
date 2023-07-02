function Save8bitStream(Filename,N,M,stream)
% Filename -> ficheiro onde se irá colocar os dados
% N -> número de linhas
% M -> número de colunas
% stream -> array com a informação de uma imagem compressada
    fp = fopen(Filename,"wb");
    fwrite(fp,N,'uint16');
    fwrite(fp,M,'uint16');
    fwrite(fp,stream,'uint8');
    fclose(fp);
end