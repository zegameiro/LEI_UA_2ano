function [N, M, Im] = Load8bitImage(Filename)
% Filename -> nome do ficheiro a utilizar
    
    fid = fopen(Filename,"rb");
    N = fread(fid,1,"uint16");
    M = fread(fid,1,"uint16");
    Im = fread(fid,[N,M],"uint8");
    fclose(fid);
    
end