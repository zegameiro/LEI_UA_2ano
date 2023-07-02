function [N, M, stream] = Load8bitStream(Filename)
% Filename -> Nome do ficheiro
% stream -> array com a informação de uma imagem compressada
    fp = fopen(Filename,"rb");
    N = fread(fp,1,"uint16");
    M = fread(fp,1,"uint16");
    stream = fread(fp,"uint8");
    fclose(fp);

end