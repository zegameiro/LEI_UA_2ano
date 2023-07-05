function [x,Ta] = Reconstroi(X,f)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
    N = length(X);
    x = ifft(ifftshift(X)) * N;

    fa = max(abs(f)) * 2;
    Ta = 1 / fa;
end