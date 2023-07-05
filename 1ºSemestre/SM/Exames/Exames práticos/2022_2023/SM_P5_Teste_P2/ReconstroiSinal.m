function [t,yt] = ReconstroiSinal(x,Ta)
% x -> Vetor de amostras
% Ta -> Período de amostragem
% yt -> Sinal temporal

    fa = 1 / Ta;
    N = length(x);
    Tai = Ta / 100;
    t = (0:(N * 100)-1) * Tai;
    % sinc = sin(pi * fa .* t) / pi * fa .* t;  Fórmula do seno cardinal (sinc(fa * t))
    
    yt = 0;

   for i = 1:N
        yt = yt + x(i) * (sinc(fa .* (t - ((i-1) * Ta))));
   end

end