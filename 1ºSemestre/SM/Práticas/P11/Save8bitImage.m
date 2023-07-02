function Save8bitImage(Filename,Image)
    % Filename -> Nome do ficheiro em que é para guardar 
    % Image -> matriz com a informação de uma imagem
    
    fp = fopen(Filename,'wb');
    [n,m] = size(Image);
    N = uint16(n);
    M = uint16(m);
    fwrite(fp,N,'uint16');
    fwrite(fp,M,'uint16');
    fwrite(fp,Image,'uint8');
    fclose(fp);

end