function [X,f] = Espetro(x,Ta)
% x -> vetor de amostras
% Ta -> período de amostragem
% X -> vetor da mesma dimensão de x,  com os coeficientes da DFT de x(t)
% f -> vetor da mesma dimensão de x, com frequências (em Hz) de cada
% componente de X

    fa = 1 / Ta;
    N = length(x);
    df = fa/N;
    f = (0:N-1) * df - fa/2;
    
    X = fft(x);
    X = fftshift(X) / N;
    X = abs(X);
end