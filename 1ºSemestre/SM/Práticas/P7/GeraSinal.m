function [xt,t] = GeraSinal(N,Ta)
% N -> Amostras do sinal
% Ta -> Período de amostragem
% x -> Vetor de amostras do sinal
% t -> Instantes de tempo
    t = [0: (N-1)]' * Ta;
    phi = randn(N,1) * pi;
    phi_i1 = zeros(N,1);
    
    for n = 2:N
        phi_i1(n) = phi_i1(n-1) + (phi(n) + phi(n-1)) * Ta / 2;
    end

    phi1 = randn(N,1) * pi;
    phi_i2 = zeros(N,1);

    for i = 2:N
        phi_i2(i) = phi_i2(i-1) + (phi1(i) + phi(i-1)) * Ta/2;
    end

    rt = 0.5 * sin(20 * pi * t + 10 * phi_i1) + 0.5 * sin(24 * pi * t + 10 * phi_i2);
    xt = sin(2 * pi * t) + rt;
end